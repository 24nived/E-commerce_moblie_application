package com.example.navasc.data

sealed class Category(val category:String){
    object Saree: Category("Saree")
    object Blouse: Category("Blouse")
    object Lehanga: Category("Lehanga")
    object Frock: Category("Frock")
    object Tops: Category("Tops")


}
