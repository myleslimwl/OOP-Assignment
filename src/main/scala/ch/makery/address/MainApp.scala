package ch.makery.address
import ch.makery.address.model.Person
import ch.makery.address.view.PersonEditDialogController
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._
import scalafxml.core.{FXMLLoader, FXMLView, NoDependencyResolver}
import javafx.{scene => jfxs}
import scalafx.beans.property.StringProperty
import scalafx.collections.ObservableBuffer
import scalafx.scene.image.Image
import scalafx.stage.{Modality, Stage}

object MainApp extends JFXApp {
  val personData = new ObservableBuffer[Person]()

  /**
   * Constructor
   */
  personData += new Person("Hans", "Muster")
  personData += new Person("Ruth", "Mueller")
  personData += new Person("Heinz", "Kurz")
  personData += new Person("Cornelia", "Meier")
  personData += new Person("Werner", "Meyer")
  personData += new Person("Lydia", "Kunz")
  personData += new Person("Anna", "Best")
  personData += new Person("Stefan", "Meier")
  personData += new Person("Martin", "Mueller")

  // transform path of RootLayout.fxml to URI for resource location.
  val rootResource = getClass.getResource("view/RootLayout.fxml")
  // initialize the loader object.
  val loader = new FXMLLoader(rootResource, NoDependencyResolver)
  // Load root layout from fxml file.
  loader.load();
  // retrieve the root component BorderPane from the FXML
  val roots = loader.getRoot[jfxs.layout.BorderPane]
  // initialize stage
  stage = new PrimaryStage {
    title = "AddressApp"
    icons += new Image(getClass.getResourceAsStream("/images/book.png"))
    scene = new Scene {
      stylesheets += getClass.getResource("view/DarkTheme.css").toString
      root = roots
    }
  }
  // actions for display person overview window
  def showPersonOverview() = {
    val resource = getClass.getResource("view/PersonOverview.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  def showWelcome() = {
    val resource = getClass.getResource("view/Welcome.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  def showPersonEditDialog(person: Person): Boolean = {
    val resource = getClass.getResourceAsStream("view/PersonEditDialog.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val roots2  = loader.getRoot[jfxs.Parent]
    val control = loader.getController[PersonEditDialogController#Controller]

    val dialog = new Stage() {
      initModality(Modality.ApplicationModal)
      initOwner(stage)
      scene = new Scene {
        stylesheets += getClass.getResource("view/DarkTheme.css").toString
        root = roots2
      }
    }
    control.dialogStage = dialog
    control.person = person
    dialog.showAndWait()
    control.okClicked
  }

  // call to display PersonOverview when app start
  showWelcome()

  val str1 = new StringProperty("hello") //publisher
  val str2 = new StringProperty("sunway") //subscriber
  val str3 = new StringProperty("water") //subscriber

  str2 <== str1
  str3 <== str1
  println(str2())
  println(str3.value)

  println(((a: Int) => { a + 1})(4))
  val inc1: Function1[Int, Int] = (a: Int) => {a + 1}
  val inc1a: (Int) => Int = _ + 1
  val sum: (Int, Int) => Int = (a, b) => a + b
  val suma: (Int, Int) => Int = _ + _
  println(inc1(5))

  implicit val whatever: Int = 3

  def multiply(value: Int)(implicit by: Int): Int = value * by

  println(multiply(4))

  class Square(val length: Int) {
    def area: Int = length * length
  }

  implicit def autoconvertsquare(length: Int): Square = {
    new Square(length)
  }

  def printSquareArea(sq: Square): Unit = {
    println("The area of square is " + sq.area)
  }

  printSquareArea(6)
}