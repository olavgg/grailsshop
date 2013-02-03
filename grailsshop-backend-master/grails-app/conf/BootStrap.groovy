class BootStrap {
    
    def mainInitService

    def init = { servletContext ->
        environments {
            production {
            }
            test{
                developmentSetup()
            }
            development {
                developmentSetup()
            }
        }
        
    }
    
    def developmentSetup(){
        mainInitService.init()
    }
    
    def destroy = {
    }
}
