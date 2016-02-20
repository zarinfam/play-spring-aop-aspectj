package configs.aspects

import models.Monitoring
import play.api.libs.iteratee.{Iteratee, Enumerator}
import services.MonitoringService
import scala.Predef
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.Future
import scala.util.{Success, Failure}

/**
  * Created by saeed on 1/4/16.
  */
class EnumeratorHelper {

  def getEnumeratorFuture(body: Enumerator[Array[Byte]]) ={
    Iteratee.flatten(body |>> Iteratee.consume[Array[Byte]]()).run
  }

}
