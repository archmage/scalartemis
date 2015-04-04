package marad.scalartemis

import ComponentTypeManager.ComponentClass

import scala.collection.BitSet
import scala.reflect.{ClassTag, classTag}

trait Aspect {
  def ~(types: BitSet): Boolean
}

case class AspectAll(types: ComponentType*) extends Aspect {
  val set = BitSet(types.map(_.id):_*)
  override def ~(typeSet: BitSet): Boolean = (set & typeSet) == set
}

case class AspectOneOf(types: ComponentType*) extends Aspect {
  val set = BitSet(types.map(_.id):_*)
  override def ~(types: BitSet): Boolean = (set & types).nonEmpty
}

case class AspectOnly(types: ComponentType*) extends Aspect {
  val set = BitSet(types.map(_.id):_*)
  override def ~(types: BitSet): Boolean = (set ^ types).isEmpty
}

object AspectAny extends Aspect {
  override def ~(types: BitSet): Boolean = true
}

object AspectNone extends Aspect {
  override def ~(types: BitSet): Boolean = false
}

object Aspect {
  def forAll(componentClasses: ComponentClass*): Aspect =
    new AspectAll(componentClasses.map(ComponentTypeManager.getTypeFor):_*)
  def forAll[A <:Component : ClassTag]: Aspect = new AspectAll(getType[A])
  def forAll[A <:Component : ClassTag, B <: Component : ClassTag]: Aspect =
    new AspectAll(getType[A], getType[B])

  def forOneOf(componentClasses: ComponentClass*): Aspect =
    new AspectOneOf(componentClasses.map(ComponentTypeManager.getTypeFor):_*)
  def forOneOf[A <:Component : ClassTag]: Aspect = new AspectOneOf(getType[A])
  def forOneOf[A <:Component : ClassTag, B <:Component : ClassTag]: Aspect =
    new AspectOneOf(getType[A], getType[B])

  def onlyFor(componentClasses: ComponentClass*): Aspect =
    new AspectOnly(componentClasses.map(ComponentTypeManager.getTypeFor):_*)
  def onlyFor[A <:Component : ClassTag]: Aspect = new AspectOnly(getType[A])
  def onlyFor[A <:Component : ClassTag, B <:Component : ClassTag]: Aspect =
    new AspectOnly(getType[A], getType[B])

  def any = AspectAny
  def none = AspectNone

  private def getType[A <: Component : ClassTag]: ComponentType =
    ComponentTypeManager.getTypeFor(
      classTag[A].runtimeClass.asSubclass(classOf[Component])
    )
}