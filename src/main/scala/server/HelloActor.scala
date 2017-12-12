package server

import akka.actor.{Actor, ActorSystem, Props}
import akka.serialization.Serialization
import com.typesafe.config.ConfigFactory

class HelloActor extends Actor {
  override def preStart(): Unit = {
    println("Hello actor started")
    println(Serialization.serializedActorPath(self))
  }

  def receive = {
    case "hello" =>
      println("******** came to server hello ***********")
      sender() ! "Hello from the other side"
    case "shutdown" => context.stop(self)
    case _       => println("huh?")
  }
}

object Server extends App {
  val root = ConfigFactory.load()
  val one  = root.getConfig("systemOne")
  val system = ActorSystem("HelloSystem", one)
  val helloActor = system.actorOf(Props[HelloActor], "HelloActor")
}
