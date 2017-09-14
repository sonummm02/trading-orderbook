package com.paritytrading.parity.ticker.repository;

import java.io.Serializable;
import java.util.UUID;

import com.paritytrading.parity.ticker.dto.TickerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TickerRepository extends JpaRepository<TickerDTO,UUID>
{

}
