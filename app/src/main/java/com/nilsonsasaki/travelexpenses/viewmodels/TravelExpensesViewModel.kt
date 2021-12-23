package com.nilsonsasaki.travelexpenses.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nilsonsasaki.travelexpenses.R
import com.nilsonsasaki.travelexpenses.models.TravelValues

class TravelExpensesViewModel : ViewModel() {

    val toastMessage = MutableLiveData<Int>()
    val travelValues: MutableLiveData<TravelValues> = MutableLiveData<TravelValues>()

    fun validationToast() {
        toastMessage.value = R.string.testToast //testing observable
    }
}