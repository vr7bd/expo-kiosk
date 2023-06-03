import ExpoModulesCore

public class ExpoKioskModule: Module {

  public func definition() -> ModuleDefinition {
   
    Name("ExpoKiosk")

      Function("killApp") {
          return "Hello world! 👋"
      }
      Function("killApp") {
          return "Hello world! 👋"
      }
       
  }
}
