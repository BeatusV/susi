import collection.mutable.Stack
import org.scalatestplus.play._
/**
  * Created by peter on 29-1-17.
  */
class DatabaseSpec extends PlaySpec{
  "A Stack" must {
    "pop values in last in first out order " in {
      val stack = new Stack[Int]
      stack.push(1)
      stack.push(2)
      stack.pop() mustBe 2
      stack.pop() mustBe 1
    }
    "throw NoSucElement if an empty stack is popped" in {
      val emptyStack = new Stack[Int]
      a [NoSuchElementException] mustBe thrownBy{
        emptyStack.pop()
      }
    }
  }
}
