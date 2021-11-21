package jouter

import scala.language.implicitConversions
import scala.util.{Failure, Try}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2021/11/21 18:04
 */
sealed trait Router {
  type Args;
}

case class Action[A](action: A=>Try[Unit]) extends Router {
  override type Args = A
  def apply(implicit argv: A): Try[Unit] = action(argv)
}
object Action {
  implicit def convert[A](action: A=>Try[Unit]):Action[A] = Action(action)
}

case class Route[A](routes: (String, Router)*) extends Router {
  override type Args = A
  var router: Map[String, Router] = Map() ++ routes

  def dispatch(command: String)(implicit args: A): Try[Unit] = {
    if(command.contains('.')){
      val tokens = command.split('.')
      dispatch(tokens)
    } else {
      router(command).asInstanceOf[Action[A]].apply
    }
  }

  def dispatch(commands: Array[String])(implicit args: A): Try[Unit] = {
    if(commands.isEmpty){
      Failure(new NoteFound(f"expect a command path but get empty array"))
    } else {
      val key = commands.head
      val rest = commands.tail
      if(rest.isEmpty){
        router(key).asInstanceOf[Action[A]].apply(args)
      } else {
        router(key).asInstanceOf[Route[A]].dispatch(rest)
      }
    }
  }

  def tasks(): Seq[String] = {
    router.foldLeft(Seq[String]()) { (acc, element) =>
      acc ++ (tasks(element._1, element._2))
    }
  }

  def tasks(base: String, next: Router): Seq[String] = {
    next match {
      case _:Action[_] => Seq(base)
      case sub: Route[_] => sub.tasks().map(path=>f"$base.$path")
    }
  }

}


