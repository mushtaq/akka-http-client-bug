package com.example

import akka.actor.testkit.typed.scaladsl.ActorTestKit
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.adapter._
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{Matchers, WordSpec}

import scala.concurrent.Await
import scala.concurrent.duration.DurationDouble

class UserRoutesSpec
    extends WordSpec
    with Matchers
    with ScalaFutures
    with ScalatestRouteTest {

  lazy val testKit = ActorTestKit()
  implicit def typedSystem: ActorSystem[Nothing] = testKit.system
  override def createActorSystem(): akka.actor.ActorSystem =
    testKit.system.toClassic

  "UserRoutes" should {
    "demo" in {
      val request = HttpRequest(uri = "http://127.0.0.1:8080/users?id=100")
      val request2 = HttpRequest(uri = "http://127.0.0.1:8080/users")

      Await.result(Http().singleRequest(request), 5.seconds)
      Await.result(Http().singleRequest(request2), 5.seconds)
    }
  }
}
