package services

import controllers.responses.OfficeResponse

import javax.inject.Singleton

@Singleton class OfficeServiceImpl {


   def populate(officeID: Option[Long]): Option[OfficeResponse] = officeID match {
    case Some(value) => Some(OfficeResponse(value, ""))
    case None => None
  }


}
