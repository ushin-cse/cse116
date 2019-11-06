package Battle_GUI.view


import io.socket.client.{IO, Socket}
import io.socket.emitter.Emitter
import javafx.application.Platform
import javafx.event.{ActionEvent, EventHandler}
import play.api.libs.json.{JsValue, Json}
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.control.{Button, TextField}
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Rectangle, Shape}




class HandleMessagesFromServer() extends Emitter.Listener {
  override def call(objects: Object*): Unit = {

    // Use runLater when interacting with the GUI
    Platform.runLater(() => {
      // This method is called whenever a new game state is received from the server

      //TODO: Make this json, from what Jacob said you can make a state var

      import com.google.gson.Gson

      //      case class party1 (party: String, name: String, hp: Int, battleOptions: List[String])
      //      case class party2 (party: String, name: String, hp: Int, battleOptions: List[String])

      object loveJson extends App {

        case class player1_red(name:String, hp: Int, battleOptions: Int)
        case class player2_red(name:String, hp: Int, battleOptions: Int)
        case class player3_red(name:String, hp: Int, battleOptions: Int)
        case class player4_red(name:String, hp: Int, battleOptions: Int)

        case class player1_blue(name:String, hp: Int, battleOptions: Int)
        case class player2_blue(name:String, hp: Int, battleOptions: Int)
        case class player3_blue(name:String, hp: Int, battleOptions: Int)
        case class player4_blue(name:String, hp: Int, battleOptions: Int)

        val p1_red = player1_red("RedPlayer1", 50, 10)
        val p2_red = player2_red("RedPlayer2", 50, 22)
        val p3_red = player3_red("RedPlayer3", 50, 13)
        val p4_red = player4_red("RedPlayer4", 50, 11)

        val p1_blue = player1_blue("BluePlayer1", 50, 19)
        val p2_blue = player2_blue("BluePlayer2", 50, 12)
        val p3_blue = player3_blue("BluePlayer3", 50, 14)
        val p4_blue = player4_blue("BluePlayer4", 50, 18)

        val change = new Gson

        val redPlayer11 = change.toJson(p1_red)
        val redPlayer22 = change.toJson(p2_red)
        val redPlayer33 = change.toJson(p3_red)
        val redPlayer44 = change.toJson(p4_red)

        val BluePlayer11 = change.toJson(p4_blue)
        val BluePlayer22 = change.toJson(p4_blue)
        val BluePlayer33 = change.toJson(p4_blue)
        val BluePlayer44 = change.toJson(p4_blue)

        val redPlayer1 = redPlayer11.toString
        val redPlayer2 = redPlayer22.toString
        val redPlayer3 = redPlayer33.toString
        val redPlayer4 = redPlayer44.toString

        val BluePlayer1 = BluePlayer11.toString
        val BluePlayer2 = BluePlayer22.toString
        val BluePlayer3 = BluePlayer33.toString
        val BluePlayer4 = BluePlayer44.toString

        println(BluePlayer4)
      }

      //TODO: JsValue to Stringify

      val data: JsValue = Json.parse("""{
       |  "playerParty": {
       |    "characters":
       |      [
       |        {"name":"RedPlayer1", "hp":50, "maxHP":70, "battleOptions": ["ShieldDefence","ThunderSword","KnightStrike","RegenHP"]},
       |        {"name":"RedPlayer2", "hp":50, "maxHP":70, "battleOptions": ["ShieldDefence","ThunderSword","KnightStrike","RegenHP"]},
       |        {"name":"RedPlayer3", "hp":50, "maxHP":70, "battleOptions": ["ShieldDefence","ThunderSword","KnightStrike","RegenHP"]},
       |        {"name":"RedPlayer4", "hp":50, "maxHP":70, "battleOptions": ["ShieldDefence","ThunderSword","KnightStrike","RegenHP"]}
       |      ]
       |  },
       |
       |  "enemyParty": {
       |    "characters":
       |    [
       |      {"name":"RedPlayer1", "hp":50, "maxHP":70, "battleOptions": ["LionStrike", "LightSaga", "Roar", "RegenMP"]},
       |      {"name":"RedPlayer2", "hp":50, "maxHP":70, "battleOptions": ["LionStrike", "LightSaga", "Roar", "RegenMP"]},
       |      {"name":"RedPlayer3", "hp":50, "maxHP":70, "battleOptions": ["LionStrike", "LightSaga", "Roar", "RegenMP"]},
       |      {"name":"RedPlayer4", "hp":50, "maxHP":70, "battleOptions": ["LionStrike", "LightSaga", "Roar", "RegenMP"]}
       |    ]
       |  }
       |}""".stripMargin)

      //val ShieldDefence: Double = (parsed\ "Shield Defence").as[Double]  // current Shield Defence


      //TODO BattleOptions

      val ShieldDefence: Double = (data \ "playerParty" \ "characters" \ 0 \ "battleOptions").as[Double] // Number Owned
      val ThunderSword: Double = (data \ "playerParty" \ "characters" \ 0 \ "battleOptions").as[Double] // Number Owned
      val KnightStrike: Double = (data \ "enemyParty" \ "characters" \ 0 \ "battleOptions").as[Double] // Number Owned
      val RegenHP: Double = (data \ "enemyParty" \ "characters" \ 0 \ "battleOptions").as[Double]

      val LionStrike: Double = (data \ "enemyParty" \ "characters" \ 0 \ "battleOptions").as[Double] // Number Owned
      val LightSaga: Double = (data \ "enemyParty" \ "characters" \ 0 \ "battleOptions").as[Double] // Number Owned
      val Roar: Double = (data \ "enemyParty" \ "characters" \ 0 \ "battleOptions").as[Double] // Number Owned
      val RegenMP: Double = (data \ "enemyParty" \ "characters" \ 0 \ "battleOptions").as[Double]

      val redCharacters: List[JsValue] = (data \ "playerParty" \ "characters").as[List[JsValue]]
      val BlueCharacters: List[JsValue] = (data \ "enemyParty" \ "characters").as[List[JsValue]]


      //      for (redPlayer1 <- redCharacters) {
      //        val hp: Int = (redPlayer1 \ "hp").as[Int]
      //        val battleOptions: String = (redPlayer1 \ "hp" \ "maxHP" \ "ShieldDefence").as[String]
      //        val MaxHp: Int = (redPlayer1 \ "hp" \ "maxHP").as[Int]
      //}

      //TODO: Display Name

      val ReadyPlayerRed1:String = (data \ "playerParty" \ "name").as[String]
      val ReadyPlayerRed2:String = (data \ "playerParty" \ "name").as[String]
      val ReadyPlayerRed3:String = (data \ "playerParty" \ "name").as[String]
      val ReadyPlayerRed4:String = (data \ "playerParty" \ "name").as[String]

      val ReadyPlayerBlue1:String = (data \ "enemyParty" \ "name").as[String]
      val ReadyPlayerBlue2:String = (data \ "enemyParty" \ "name").as[String]
      val ReadyPlayerBlue3:String = (data \ "enemyParty" \ "name").as[String]
      val ReadyPlayerBlue4:String = (data \ "enemyParty" \ "name").as[String]

      DesktopGUI.playerPartyRed.text = ReadyPlayerRed1.toString
      DesktopGUI.playerPartyRed.text = ReadyPlayerRed2.toString
      DesktopGUI.playerPartyRed.text = ReadyPlayerRed3.toString
      DesktopGUI.playerPartyRed.text = ReadyPlayerRed4.toString

      DesktopGUI.enemyPartyRed.text = ReadyPlayerBlue1.toString
      DesktopGUI.enemyPartyRed.text = ReadyPlayerBlue2.toString
      DesktopGUI.enemyPartyRed.text = ReadyPlayerBlue3.toString
      DesktopGUI.enemyPartyRed.text = ReadyPlayerBlue4.toString

      //TODO DeskTop GUI

      DesktopGUI.LionStrike_Button.text = LionStrike.toString
      DesktopGUI.LightSaga_Button.text = LightSaga.toString
      DesktopGUI.Roar_Button.text = Roar.toString
      DesktopGUI.RegenMP_Button.text = RegenMP.toString

      DesktopGUI.ShieldDefence_Button.text = ShieldDefence.toString
      DesktopGUI.ThunderSword_Button.text = ThunderSword.toString
      DesktopGUI.KnightStrike_Button.text = KnightStrike.toString
      DesktopGUI.RegenHP_Button.text = RegenHP.toString


      // TODO: Display the game state on your GUI
      // You must display: current gold, and the name, number owned, and cost for each type of equipment

      // You can access any variables/methods in the DesktopGUI object from this class
      // ex. DesktopGUI.goldTextField.text = goldFromGameState

    })
  }
}


object DesktopGUI extends JFXApp {

  var socket: Socket = IO.socket("https://tictactoe.info/")
  socket.on("gameState", new HandleMessagesFromServer)
  socket.connect()

  //TODO emit char?

  // Call this method whenever the user clicks your gold button
  def clickStart(): Unit = {
    socket.emit("clickStart")
  }

  def BattleOptions(options: String): Unit = {
    socket.emit("What do you want to pick",options)
  }

  //  disPlay.text.value = "HP increased by 5"

  val circle1: Rectangle = new Rectangle {
    x = 60.0
    y = 50.0
    width = 140.0
    height = 140.0
    translateX = 300.0
    translateY = -300.0
    fill = Color.Red
  }

  val circle2: Rectangle = new Rectangle {
    x = 60.0
    y = 50.0
    width = 140.0
    height = 140.0
    translateX = 300.0
    translateY = -250.0
    fill = Color.Red
  }

  val circle3: Rectangle = new Rectangle {
    x = 60.0
    y = 50.0
    width = 140.0
    height = 140.0
    translateX = 300.0
    translateY = -200.0
    fill = Color.Red
  }

  val circle4: Rectangle = new Rectangle {
    x = 60.0
    y = 50.0
    width = 140.0
    height = 140.0
    translateX = 300.0
    translateY = -150.0
    fill = Color.Red
  }

  val rectangle2: Rectangle = new Rectangle{
    x = 60.0
    y = 50.0
    width = 140.0
    height = 140.0
    translateX = 540.0
    translateY = -623.0
    fill = Color.Blue
  }

  val rectangle3: Rectangle = new Rectangle{
    x = 60.0
    y = 50.0
    width = 140.0
    height = 140.0
    translateX = 540.0
    translateY = -953.0
    fill = Color.Blue
  }

  val rectangle4: Rectangle = new Rectangle{
    x = 60.0
    y = 50.0
    width = 140.0
    height = 140.0
    translateX = 540.0
    translateY = -1283.0
    fill = Color.Blue
  }

  val rectangle1: Rectangle = new Rectangle{
    x = 60.0
    y = 50.0
    width = 140.0
    height = 140.0
    translateX = 540.0
    translateY = -293.0
    fill = Color.Blue
  }

  val playerPartyRed: TextField = new TextField {    //TODO Mainly using this
    editable = false
    translateY = -345
    style = "-fx-font: 12 ariel;"
    text = "Red Player1"
  }

  val enemyPartyRed: TextField = new TextField {
    translateX = 820.0
    translateY = -370
    editable = false
    style = "-fx-font: 12 ariel;"
    text = "Blue Player2"
  }

  val enemyParty: TextField = new TextField {
    editable = false
    style = "-fx-font: 15 ariel;"
    text = "Knight Strike"
  }

  val LionStrike_Button: Button = new Button {
    translateX = 820.0
    translateY = 769.0
    style = "-fx-font: 25 ariel;"
    text = "LionStrike"
    onAction = new LionStrike(playerPartyRed)
  }

  val LightSaga_Button: Button = new Button {
    translateX = 820.0
    translateY = 673.0
    style = "-fx-font: 25 ariel;"
    text = "LightSaga"
    onAction = new LightSaga(playerPartyRed)
  }

  val Roar_Button: Button = new Button {
    translateX = 820.0
    translateY = 577.0
    style = "-fx-font: 25.8 ariel;"
    text = "RoarSaga"
    onAction = new Roar(playerPartyRed)
  }

  val RegenMP_Button: Button = new Button {
    translateX = 820.0
    translateY = 481.0
    style = "-fx-font: 25.8 ariel;"
    text = "RegenMP"
    onAction = new RegenMP(playerPartyRed)
  }

  //TODO: BattleOption Chapter 2

  val ShieldDefence_Button: Button = new Button {
    translateY = 573.0
    style = "-fx-font: 25.54 ariel;"
    text = "ShieldGain"
    onAction = new shieldDefence(playerPartyRed)
  }

  val KnightStrike_Button: Button = new Button {
    translateY = 476.0
    style = "-fx-font: 26 ariel;"
    text = "KnightBite"
    onAction = new knightStrike(playerPartyRed)
  }

  val ThunderSword_Button: Button = new Button {
    translateY = 379.0
    style = "-fx-font: 25 ariel;"
    text = "ThunderZz"
    onAction = new thunderSword(playerPartyRed)
  }

  val RegenHP_Button: Button = new Button {
    translateY = 282.0
    style = "-fx-font: 25.54 ariel;"
    text = "RegenHeal"
    onAction = new regenHP(playerPartyRed)
  }

  val verticalBox: VBox = new VBox() {
    children = List(LionStrike_Button, LightSaga_Button, Roar_Button, RegenMP_Button,
      ShieldDefence_Button, KnightStrike_Button, ThunderSword_Button, RegenHP_Button,
      playerPartyRed, enemyPartyRed,
      circle1, circle2, circle3, circle4, rectangle1, rectangle2, rectangle3, rectangle4)
  }
  var hp = 1

  var circle1b: List[Shape] = List(circle1) // red
  var circle2b: List[Shape] = List(circle2) // Orange
  var circle3b: List[Shape] = List(circle3) // yellow
  var circle4b: List[Shape] = List(circle4) // blue
  var NewMoveRec: List[Shape] = List(rectangle1)        //Pink
  var NewMoveRec2: List[Shape] = List(rectangle2)       //Maroon
  var NewMoveRec3: List[Shape] = List(rectangle3)       //Purple
  var NewChangeRecColor: List[Shape] = List(rectangle4)   //Green

  //this.stage = new AnimationTimer(Long => Unit = (time: Long) => { //Time is current time
  //  for (i <- NewChangeRecColor) {
  //    val duration = (System.nanoTime - t1)
  //    if (duration >= 5) {
  //      i.fill = Color.Green
  //      val t1 = System.nanoTime.equals(1)
  //    }
  //
  //  }
  //}


  DesktopGUI.hp

  val updateRec1: Long => Unit = (time: Long) => { //Time is current time
    for (i <- circle3b) {
      if (hp >= 0) {
        i.rotate.value += 60
      }
    }
  }


  val updateRec2: Long => Unit = (time: Long) => { //Time is current time
    for (i <- circle4b) {
      if (hp >= 0) {
        i.rotate.value += 60
        i.fill = Color.Purple
      }
    }
  }

  val changeColor1: Long => Unit = (time: Long) => { //Time is current time
    for (i <- circle1b) {
      if (hp >= 0) {
        i.rotate.value += 60
        i.fill = Color.Green
      }
    }
  }

  val changeColor2: Long => Unit = (time: Long) => { //Time is current time
    for (i <- circle2b) {
      if (hp >= 0) {
        i.rotate.value += 60
      }
    }
  }

  val updateRecColor1: Long => Unit = (time: Long) => { //Time is current time
    for (i <- NewMoveRec) {
      if (hp >= 0) {
        i.rotate.value += 60
      }
    }
  }

  val updateRecMove1: Long => Unit = (time: Long) => { //Time is current time
    for (i <- NewMoveRec2) {
      if (hp >= 0) {
        i.rotate.value += 60
      }
    }
  }

  val updateRecMove2: Long => Unit = (time: Long) => { //Time is current time
    for (i <- NewMoveRec3) {
      if (hp >= 0) {
        i.rotate.value += 60
      }
    }
  }

  val updateRecMove3: Long => Unit = (time: Long) => { //Time is current time
    for (i <- NewChangeRecColor) {
      if (hp >= 0) {
        i.rotate.value += 60
        i.fill = Color.Pink
      }
    }
  }

  this.stage = new PrimaryStage {
    this.title = "PLEASE PASS CSE!!!"
    scene = new Scene() {
      fill = Color.Black
      content = List(verticalBox)
    }
  }

    var anime1 = AnimationTimer(DesktopGUI.updateRec1)    // Yellow
    var anime2 = AnimationTimer(DesktopGUI.updateRec2)    //BLUE

    var anime3 = AnimationTimer(DesktopGUI.changeColor1)  // Red(circle1b)
    var anime4 = AnimationTimer(DesktopGUI.changeColor2)  // Orange(circle2b)

    var animeChangeRecColor = AnimationTimer(DesktopGUI.updateRecColor1)
    var animeChangeRecMove1 = AnimationTimer(DesktopGUI.updateRecMove1)  //TODO: Start rotate
    var animeChangeRecMove2 = AnimationTimer(DesktopGUI.updateRecMove2)
    var animeChangeRecMove3 = AnimationTimer(DesktopGUI.updateRecMove3)


  // Timer Start \\

    System.currentTimeMillis()

  // Timer End \\

  DesktopGUI.anime1.stop()      //Yellow
  var start1: Boolean = true

  DesktopGUI.anime2.stop()      //BLUE
  var start2: Boolean = true

  DesktopGUI.anime3.stop()       //RED
  var start3: Boolean = true

  DesktopGUI.anime4.stop()       //ORANGE
  var start4: Boolean = true


  DesktopGUI.animeChangeRecColor.stop()
  var start5: Boolean = true

  DesktopGUI.animeChangeRecMove1.stop()
  var start6: Boolean = true

  DesktopGUI.animeChangeRecMove2.stop()
  var start7: Boolean = true

  DesktopGUI.animeChangeRecMove3.stop()
  var start8: Boolean = true

}

//TODO: Textboxes and TextField

// ["LionStrike", "LightSaga", "Roar", "RegenMP"]

class LionStrike(enemyParty: TextField) extends EventHandler[ActionEvent] {
  override def handle(event: ActionEvent): Unit = {
    if (DesktopGUI.start2) {
      DesktopGUI.start2 = false
      DesktopGUI.anime2.start()
    }
    else {
      DesktopGUI.start2 = true
      DesktopGUI.anime2.stop()
    }
    DesktopGUI.BattleOptions("lionStrike")
  }
}

  class LightSaga(enemyParty: TextField) extends EventHandler[ActionEvent] {
    override def handle(event: ActionEvent): Unit = {
      if (DesktopGUI.start1) {
        DesktopGUI.start1 = false
        DesktopGUI.anime1.start()
      }
      else {
        DesktopGUI.start1 = true
        DesktopGUI.anime1.stop()
      }
      DesktopGUI.BattleOptions("LightSaga")
    }
  }

    class Roar(enemyParty: TextField) extends EventHandler[ActionEvent] {
      override def handle(event: ActionEvent): Unit = {
        if (DesktopGUI.start4) {
          DesktopGUI.start4 = false
          DesktopGUI.anime4.start()
        }
        else {
          DesktopGUI.start4 = true
          DesktopGUI.anime4.stop()
        }
        DesktopGUI.BattleOptions("Roar")
      }
    }

    class RegenMP(enemyParty: TextField) extends EventHandler[ActionEvent] {
      override def handle(event: ActionEvent): Unit = {
        if (DesktopGUI.start8) {
          DesktopGUI.start8 = false
          DesktopGUI.animeChangeRecMove3.start()
        }
        else {
          DesktopGUI.start8 = true
          DesktopGUI.animeChangeRecMove3.stop()
        }
        DesktopGUI.BattleOptions("RegenMP")
      }
    }

// -------------------------- \\  TODO: This is good!

class shieldDefence(playerParty: TextField) extends EventHandler[ActionEvent] {
  override def handle(event: ActionEvent): Unit = {
    if (DesktopGUI.start2){
      DesktopGUI.start2 = false
      DesktopGUI.anime2.start()
    }

    else{
      DesktopGUI.start2 = true
      DesktopGUI.anime2.stop()
    }
  }
}

class knightStrike(playerParty: TextField) extends EventHandler[ActionEvent] {
  override def handle(event: ActionEvent): Unit = {

    if (DesktopGUI.start6){
      DesktopGUI.start6 = false
      DesktopGUI.animeChangeRecMove1.start()
    }

    else{
      DesktopGUI.start6 = true
      DesktopGUI.animeChangeRecMove1.stop()
    }

    DesktopGUI.BattleOptions("knightStrike")  // Don't know why this is working..
  }
}

class thunderSword(playerParty: TextField) extends EventHandler[ActionEvent] {
  override def handle(event: ActionEvent): Unit = {
    if (DesktopGUI.start7){
      DesktopGUI.start7 = false
      DesktopGUI.animeChangeRecMove2.start()
    }

    else{
      DesktopGUI.start7 = true
      DesktopGUI.animeChangeRecMove2.stop()
    }
    DesktopGUI.BattleOptions("thunderSword")
  }
}

class regenHP(playerParty: TextField) extends EventHandler[ActionEvent] {
  override def handle(event: ActionEvent): Unit = {
    if (DesktopGUI.start3) {
      DesktopGUI.start3 = false
      DesktopGUI.anime3.start()
    }

    else {
      DesktopGUI.start3 = true
      DesktopGUI.anime3.stop()
    }
    DesktopGUI.BattleOptions("regenHP")
  }
}