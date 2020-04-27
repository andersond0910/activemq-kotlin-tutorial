package activemq.tutorial

open class Email{
	var to:String = ""
	var body:String = ""
	constructor(){}
	constructor(to:String,body:String){
		this.to = to
		this.body = body
	}
	
	override fun toString() : String{
		return "Email(to:${to},body:${body})"
	}
}
