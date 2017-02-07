package sol8;

public class Template {
	private String template;
	
	public Template(String template){
		this.template = template;
	}
	public String fill(Dictionary dictionary){
		String newTemplate[] = template.split("/");
		String ret = "The ";
		
		
		for (int i = 1; i < newTemplate.length; i++){
			int mark = newTemplate[i].indexOf(" ", 2);
			int space = newTemplate[i].indexOf(" ", 1);
			String wokedTemplate = newTemplate[i].substring(0, mark).trim();
			
			try{
				if (wokedTemplate.equals("The")){
					ret = ret + "The ";
				}
				else if (wokedTemplate.equals("noun")){
					ret = ret + dictionary.getWord("noun") + newTemplate[i].substring(5);
				}
				else if (wokedTemplate.equals("verb")){
					ret = ret + dictionary.getWord("verb") + newTemplate[i].substring(5);
				}
				else if (wokedTemplate.equals("adjective")){
					ret = ret + dictionary.getWord("adjective") + newTemplate[i].substring(10);
				}
				else if (wokedTemplate.equals("adverb")){
					ret = ret + dictionary.getWord("adverb") + newTemplate[i].substring(7);
				}
				else if (wokedTemplate.equals(" pronoun ")){
					ret = ret + dictionary.getWord("pronoun") + newTemplate[i].substring(8);
				}
				else if (wokedTemplate.equals(" interjection ")){
					ret = ret + dictionary.getWord("interjection") + newTemplate[i].substring(13);
				}
				else {
					ret = ret + "/" + newTemplate[i].substring(0);
					System.out.print("[Error]: Unsupported category: '" + newTemplate[i].substring(1, space) + "'\r\n");
				}
			}
			catch (EmptyWordListException e) {
				ret = ret + "/" + newTemplate[i].substring(0) + "";
				System.out.print("[Error]: No remaining words of category: '" + newTemplate[i].substring(1, space) + "'\r\n");
			}
		
		}
		return ret + " ";
		// I need to adjust my separation of the template
		// They will have "." or "!" at the end. I need to account for that
		// I also need to move around "the" as its the first word and 
		// different from the others
	}
}
