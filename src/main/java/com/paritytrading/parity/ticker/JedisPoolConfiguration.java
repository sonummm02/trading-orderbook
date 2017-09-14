/*package com.paritytrading.parity.ticker;


//import com.acl.email.gateway.constant.GatewayConstants;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolConfiguration {

	private static JedisPoolConfig poolConfig = null;
	private static  JedisPool jedisPool = null;
	private static String host;
	private static int port;
	private static int timeout;
	private static String password;

	
	public JedisPoolConfiguration(){
		//final JedisPoolConfig poolConfig = buildPoolConfig();
		//jedisPool = new JedisPool("localhost", 6379);
		jedisPool = new JedisPool(new JedisPoolConfig(),host,port,timeout,password);
	}

	private static JedisPoolConfig buildPoolConfig() {
		final JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(Integer.parseInt(GatewayConstants.JEDIS_MAX_TOTAL));
		poolConfig.setMaxIdle(Integer.parseInt(GatewayConstants.JEDIS_MAX_IDLE));
		poolConfig.setMinIdle(Integer.parseInt(GatewayConstants.JEDIS_MIN_IDLE));
		poolConfig.setTestOnBorrow(Boolean.parseBoolean(GatewayConstants.JEDIS_TEST_ON_BORROW));
		poolConfig.setTestOnReturn(Boolean.parseBoolean(GatewayConstants.JEDIS_TEST_ON_RETURN));
		poolConfig.setTestWhileIdle(Boolean.parseBoolean(GatewayConstants.JEDIS_TEST_WHILE_IDLE));
		return poolConfig;
	}

	public static Jedis getJedisResource(){
	Jedis jedisResource = jedisPool.getResource();
	return jedisResource;
	}
	
	public static void returnRedisResource(Jedis resource){
		jedisPool.returnResource(resource);
	}
	
	public void destroy(){
		jedisPool.close();
	}
	
}*/