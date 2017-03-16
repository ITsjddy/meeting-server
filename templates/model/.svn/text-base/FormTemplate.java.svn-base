#set ($domain = $!domainName.substring(0,1).toLowerCase()+$!domainName.substring(1))
package $!{packageName}.modules.$!{floder}.model;

#macro (upperCase $str)
#set ($upper=$!str.substring(0,1).toUpperCase())
#set ($l=$!str.substring(1))
$!upper$!l#end
public class $!{domainName}Form {
	
	private String id;
#foreach($field in $!fields)
	private $!field.getType() $!field.getName();
#end

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	
#foreach($field in $!fields)

	public $!field.getType() get#upperCase($!field.getName())(){
		return this.$!field.getName();
	}
#end	
#foreach($field in $!fields)

	public void set#upperCase($!field.getName())($!field.getType() $!field.getName().toLowerCase()){
		this.$!field.getName()=$!field.getName().toLowerCase();
	}
#end

}
