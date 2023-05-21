package controllers.requests
import play.api.libs.json._

object OptionReads {
  implicit val intOptin: Reads[Option[Long]] = new Reads[Option[Long]]{
    override def reads(json: JsValue): JsResult[Option[Long]] = json match {
      case JsNull => JsSuccess(Option.empty)
      case JsNumber(value) =>  JsSuccess(Some(value.longValue))
      case _ => JsSuccess(Option.empty)
    }
  }

/*
  implicit val floatOptin: Reads[Option[Float]] = new Reads[Option[Float]] {
    override def reads(json: JsValue): JsResult[Option[Float]] = json match {
      case JsNull => JsSuccess(Option.empty)
      case JsNumber(value) => JsSuccess(Some(value.floatValue))

    }
  }


  implicit val bigDecimalOptin: Reads[Option[BigDecimal]] = new Reads[Option[BigDecimal]] {
    override def reads(json: JsValue): JsResult[Option[BigDecimal]] = json match {
      case JsNull => JsSuccess(Option.empty)
      case JsNumber(value) => JsSuccess(Some(value.bigDecimal))

    }
  }


  implicit val stringOptin: Reads[Option[String]] = new Reads[Option[String]] {
    override def reads(json: JsValue): JsResult[Option[String]] = json match {
      case JsNull => JsSuccess(Option.empty)
      case JsString(value) => JsSuccess(Some(value))

    }
  }


  implicit val boolOptin: Reads[Option[Boolean]] = new Reads[Option[Boolean]] {
    override def reads(json: JsValue): JsResult[Option[Boolean]] = json match {
      case JsNull => JsSuccess(Option.empty)
      case JsBoolean(value) => JsSuccess(Some(value))

    }
  }
  */

}
