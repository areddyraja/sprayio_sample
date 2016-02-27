package com.kedi.data.dao

import org.slf4j.LoggerFactory
import com.jolbox.bonecp.BoneCPDataSource

object KundaDataSource {
  val Log = LoggerFactory getLogger getClass

  private var Database: Option[slick.driver.PostgresDriver.backend.DatabaseDef] = None
  private var KundaDataSource: Option[BoneCPDataSource] = None
  val DriverClass = "org.postgresql.Driver"

  def init() = {
    val dataSource = new BoneCPDataSource
    Class.forName(DriverClass)
    dataSource setJdbcUrl ("jdbc:postgresql://localhost:5432/reddyraja")
    dataSource setUsername ("reddyraja")
    dataSource setPassword ("reddyraja")
    dataSource setPartitionCount (1)
    dataSource setMaxConnectionsPerPartition (30)
    dataSource setMinConnectionsPerPartition (1)
    dataSource setAcquireRetryAttempts (3)
    KundaDataSource = Some(dataSource)
    Database = Some(slick.driver.PostgresDriver.api.Database forDataSource dataSource)
    Log info "Loaded datastore configuration for url: [" + dataSource.getJdbcUrl + "]"
  }

  def shutDown = {
    KundaDataSource match {
      case Some(dataSource) ⇒ dataSource.close
      case _                ⇒ throw new RuntimeException("Datasource was not initialized.")
    }
  }

  def db = {
    Database match {
      case Some(dataBase) ⇒ dataBase
      case _              ⇒ throw new RuntimeException("Datasource was not initialized.")
    }
  }

}