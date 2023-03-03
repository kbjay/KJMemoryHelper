package com.kj.memory_helper.suggest_board

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kj.memory_helper.WarningRepository
import com.kj.memory_helper.db.WarningMsg


class BoardVM : ViewModel() {
    var warnings: LiveData<List<WarningMsg>> = WarningRepository.getAllAsync()
}