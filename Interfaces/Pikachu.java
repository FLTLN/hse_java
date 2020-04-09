class Pikachu implements Pokemon
{
    String name;
  
    Pikachu()
    {      
        this.name = "Pikachu";
    }
      
    public void choose() 
    {
      
        System.out.printf("%s, I choose you!\n", name);
    }
}