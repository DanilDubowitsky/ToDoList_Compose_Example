package com.example.todolist.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.todolist.R

private val extraBold = Font(R.font.inter_extra_bold, FontWeight.ExtraBold)
private val medium = Font(R.font.inter_medium, FontWeight.Medium)
private val semiBold = Font(R.font.inter_semi_bold, FontWeight.SemiBold)

val interFontFamily = FontFamily(extraBold, medium, semiBold)

val interTypography = Typography(defaultFontFamily = interFontFamily)
