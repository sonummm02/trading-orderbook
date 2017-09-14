package com.paritytrading.parity.ticker;

import static java.util.Arrays.*;
import static java.util.Comparator.*;
import static org.jvirtanen.util.Applications.*;

import com.paritytrading.foundation.ASCII;
import com.paritytrading.nassau.MessageListener;
import com.paritytrading.nassau.util.BinaryFILE;
import com.paritytrading.nassau.util.MoldUDP64;
import com.paritytrading.nassau.util.SoupBinTCP;
import com.paritytrading.parity.book.Market;
import com.paritytrading.parity.net.pmd.PMDParser;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jvirtanen.config.Configs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class StockTickerController {
	String[] args;
	@Autowired
	Environment env;

	
	//@RequestMapping(value = "/ticker", method = RequestMethod.GET)
	@PostMapping(value="/getticker", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> stockTicker(@RequestBody Map<String, Object> data)
	{
		
		Map<String, Object> result = new HashMap<String, Object>();

		String path = env.getProperty("udp-conf-path");
		boolean taq = false;
		System.out.println("data is receiving  from postman "+data);

		try {
			load(taq, path);
		} catch (ConfigException | FileNotFoundException e) {
			error(e);
		} catch (IOException e) {
			fatal(e);
		}

		
		/**
		 * 
		 * file read code ::
		 * =======path
		 * read file and iterate:
		 * 
		 * 
		 */
		
		result.put("message", "hi this is my data");
		
		return result;

	}

	
	
	
	private static String load(boolean taq, String path) throws IOException {
		List<String> instruments = new ArrayList<String>();
		try {
			listen(taq, config(path));
		} catch (ConfigException.Parse e) {
			System.out.println("inside catch : "+e);
			read(taq, new File(path), instruments);
			System.out.println("after file creation");
		}
		return "hllo";
	}

	private static String listen(boolean taq, Config config) throws IOException {
		List<String> instruments = config.getStringList("instruments");
		MarketDataListener listener = taq ? new TAQFormat() : new DisplayFormat(instruments);
		Market market = new Market(listener);
		System.out.println("mmmmmmmmmmmmmmmmmmm"+market);
		for (String instrument : instruments)
			market.open(ASCII.packLong(instrument));
		
		System.out.println("mmmmmmmmmmmmmmmmmmm"+"       "+market);

		MarketDataProcessor processor = new MarketDataProcessor(market, listener);

		String abc =listen(config, new PMDParser(processor));
		return abc;
	}

	private static String listen(Config config, MessageListener listener) throws IOException {
		if (config.hasPath("market-data.multicast-interface")) {
			System.out.println(config.hasPath("market-data.multicast-interface"));
			NetworkInterface multicastInterface = Configs.getNetworkInterface(config,
					"market-data.multicast-interface");
			InetAddress multicastGroup = Configs.getInetAddress(config, "market-data.multicast-group");
			int multicastPort = Configs.getPort(config, "market-data.multicast-port");
			InetAddress requestAddress = Configs.getInetAddress(config, "market-data.request-address");
			int requestPort = Configs.getPort(config, "market-data.request-port");
			System.out.println(multicastInterface);
			System.out.println(requestPort);

			MoldUDP64.receive(multicastInterface, new InetSocketAddress(multicastGroup, multicastPort),
			new InetSocketAddress(requestAddress, requestPort), listener);
			System.out.println(multicastInterface);
			System.out.println(listener);
			System.out.println("-------------");
		} else {
			InetAddress address = Configs.getInetAddress(config, "market-data.address");
			int port = Configs.getPort(config, "market-data.port");
			String username = config.getString("market-data.username");
			System.out.println(username);
			String password = config.getString("market-data.password");
				System.out.println("++++++++++++++++++else part");
			SoupBinTCP.receive(new InetSocketAddress(address, port), username, password, listener);
		}
		return "hi........";
	}

	private static void  read(boolean taq, File file, List<String> instruments) throws IOException {
		MarketDataListener listener = taq ? new TAQFormat() : new DisplayFormat(instruments);

		Market market = new Market(listener);
		System.out.println("==="+market);

		for (String instrument : instruments)
		{
			System.out.println("inside ::::::::::");
			market.open(ASCII.packLong(instrument));
			System.out.println("instrument="+instrument);
			System.out.println("market list:"+market);

		System.out.println("market data is : "+market.open(ASCII.packLong(instrument)));
	}
		MarketDataProcessor processor = new MarketDataProcessor(market, listener);

		
		BinaryFILE.read(file, new PMDParser(processor));
		//return "sp";
	}

	private static void usage() {
		System.err.println("Usage: parity-ticker [-t] <configuration-file>");

		System.err.println("       parity-ticker [-t] <input-file> [<instrument> ...]");

		System.exit(2);
	}

}
