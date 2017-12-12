package client

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory

class Client {

}

// Sending message back from server will go to deadLetters
// This is because the client actor system doesn't have a mailbox that replies could be posted to.


object Client extends App {
  println("started")

  val root = ConfigFactory.load()
  val two  = root.getConfig("systemTwo")
  val system = ActorSystem("mySystem", two)
  //What should be there to access HelloActor?
  val selection = system.actorSelection("akka.tcp://HelloSystem@192.168.1.4:2552/user/HelloActor")
  Thread.sleep(1000)
  selection ! "hello"
//  println(selection.anchorPath)
}
