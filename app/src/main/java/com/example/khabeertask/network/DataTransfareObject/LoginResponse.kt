package com.example.khabeertask.network.DataTransfareObject

import com.squareup.moshi.Json

data class LoginResponse(

	@Json(name="AccountId")
	val accountId: Int? = null,

	@Json(name="Activation")
	val activation: Boolean? = null,

	@Json(name="Token")
	val token: String? = null,

	@Json(name="AccountRole")
	val accountRole: Any? = null,

	@Json(name="Code")
	val code: Int? = null,

	@Json(name="Success")
	val success: Boolean? = null,

	@Json(name="VisitStatus")
	val visitStatus: Any? = null,

	@Json(name="EnglishMessage")
	val englishMessage: String? = null,

	@Json(name="PageCount")
	val pageCount: Int? = null,

	@Json(name="ArabicMessage")
	val arabicMessage: String? = null,

	@Json(name="CurrentPage")
	val currentPage: Int? = null,

	@Json(name="UserRole")
	val userRole: Any? = null,

	@Json(name="IsArabic")
	val isArabic: Any? = null,

	@Json(name="UserType")
	val userType: Int? = null,

	@Json(name="user")
	val user: User? = null
)

data class User(

	@Json(name="Email")
	val email: Any? = null,

	@Json(name="PhoneNumberConfirmed")
	val phoneNumberConfirmed: Boolean? = null,

	@Json(name="UserName")
	val userName: Any? = null,

	@Json(name="PatientImage")
	val patientImage: String? = null,

	@Json(name="LicenseNumber")
	val licenseNumber: Any? = null,

	@Json(name="HasInsurance")
	val hasInsurance: Boolean? = null,

	@Json(name="ClassId")
	val classId: Int? = null,

	@Json(name="ClassArabicName")
	val classArabicName: String? = null,

	@Json(name="LastNameAr")
	val lastNameAr: String? = null,

	@Json(name="Gender")
	val gender: Int? = null,

	@Json(name="EmailConfirmed")
	val emailConfirmed: Boolean? = null,

	@Json(name="Source")
	val source: Any? = null,

	@Json(name="MobileNumber")
	val mobileNumber: String? = null,

	@Json(name="FirstNameAr")
	val firstNameAr: String? = null,

	@Json(name="LastNameEn")
	val lastNameEn: String? = null,

	@Json(name="ClassEnglishName")
	val classEnglishName: String? = null,

	@Json(name="AspNetUsersId")
	val aspNetUsersId: Int? = null,

	@Json(name="Id")
	val id: Int? = null,

	@Json(name="FirstNameEn")
	val firstNameEn: String? = null,

	@Json(name="AmbulanceProfileId")
	val ambulanceProfileId: Any? = null,

	@Json(name="BirthDate")
	val birthDate: String? = null
)
