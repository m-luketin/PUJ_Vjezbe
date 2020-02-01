package Vjezba3_PUJ;

import Vjezba3_PUJ.classes.Publisher;
import Vjezba3_PUJ.classes.Watermeter;

public class App 
{
    public static void main (String args[])
    {
        Publisher publisher = new Publisher();
        Watermeter watermeter = new Watermeter(publisher);
        watermeter.separateThreads();
    }
}
