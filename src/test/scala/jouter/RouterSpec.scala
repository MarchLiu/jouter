package jouter

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import java.util.concurrent.atomic.AtomicInteger
import scala.util.Success

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2021/11/22 14:43
 */
class RouterSpec extends AnyFlatSpec with should.Matchers {

  import Action._

  implicit val args: String = "args"
  val counter = new AtomicInteger()

  val router: Route[String] = Route(
    "install" -> Route(
      "ohmyzsh" -> { _: String =>
        counter.incrementAndGet();
        Success(())
      },
      "bash" -> { _: String =>
        counter.incrementAndGet();
        Success()
      },
      "fish" -> { _: String =>
        counter.incrementAndGet();
        Success()
      }),
    "uninstall" -> Route(
      "ohmyzsh" -> { _: String =>
        counter.incrementAndGet();
        Success(())
      },
      "bash" -> { _: String =>
        counter.incrementAndGet();
        Success()
      },
      "fish" -> { _: String =>
        counter.incrementAndGet();
        Success()
      }))


  it should "generate all tasks list" in {
    router.tasks() should be(Seq(
      "install.ohmyzsh",
      "install.bash",
      "install.fish",
      "uninstall.ohmyzsh",
      "uninstall.bash",
      "uninstall.fish",
    ))
  }

  it should "count every task once" in {
    router.dispatch("install.ohmyzsh")
    router.dispatch("install.bash")
    router.dispatch("install.fish")
    router.dispatch("uninstall.ohmyzsh")
    router.dispatch("uninstall.bash")
    router.dispatch("uninstall.fish")

    counter.get() should be (6)
  }
}
