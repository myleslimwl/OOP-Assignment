package ch.makery.address.view

import ch.makery.address.MainApp
import scalafx.scene.control.TextField
import scalafxml.core.macros.sfxml

@sfxml
class WelcomeController (private val text1: TextField,
                         private val text2: TextField){
  text2.text <==> text1.text

  text2.text.onChange( (a,b,c) => {
    println(b + " change to " + c)
  })

  def getStart(): Unit = {
    MainApp.showPersonOverview()
  }
}