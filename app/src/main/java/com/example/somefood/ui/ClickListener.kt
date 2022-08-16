package com.example.somefood.ui

import com.example.somefood.data.model.ProductListModel

sealed class Click
class ToFavorite(val item: ProductListModel) : Click()
class OpenDetail(val item: ProductListModel) : Click()
class AddToBuy(val item: ProductListModel) : Click()