package com.nemesisprotocol.cryptocraze.data.database.portfolio

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nemesisprotocol.cryptocraze.domain.portfolio.CryptoInvestment

@Database(entities = [CryptoInvestment::class], version = 2, exportSchema = false)
abstract class PortfolioDatabase : RoomDatabase() {
    abstract fun portfolioDao(): PortfolioDao
}
