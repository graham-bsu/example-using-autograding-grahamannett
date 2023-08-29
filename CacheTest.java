/*
 * Design and write a test program, CacheTest.java, that uses the WebpageGenerator and the Cache to process Webpages as described in the next bullet. The usage should be as follows:
java CacheTest <cache-size> <number-of-Webpages>  
               <standard-deviation> <debug-level=0-3> [<seed>] 
where:
<cache size>: The size of the cache.
<number-of-Webpages>: The amount of Webpages to generate.
<standard-deviation>: The standard deviation of Webpage generation.
<debug-level=0,1,2,3>: Level of debug to output:
<0>: Prints out cache stats (Figure 1)
<1>: Prints out the the Webpage distribution (Figure 2)
<2>: Prints out summarized content of the Webpages (Figure 3)
<3>: Prints out the whole content of the Webpages
[<seed>]:  Square brackets denote an optional argument. Using a seed ensures simulation can be repeated for a random number generator
 */


public class CacheTest {
    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Usage: java CacheTest <cache-size> <number-of-Webpages> <standard-deviation> <debug-level=0-3> [<seed>]");
            System.exit(1);
        }
        int cacheSize = Integer.parseInt(args[0]);
        int numWebpages = Integer.parseInt(args[1]);
        double stdDev = Double.parseDouble(args[2]);
        int debugLevel = Integer.parseInt(args[3]);
        int seed = 0;
        if (args.length == 5) {
            seed = Integer.parseInt(args[4]);
        }
        
        WebpageGenerator generator = new WebpageGenerator(numWebpages, stdDev, seed);
        Cache<String, Webpage> cache = new Cache<String, Webpage>(cacheSize);
        long timeStart = System.currentTimeMillis();
        for (int i = 0; i < numWebpages; i++) {
            
            String url = generator.getURL();
            Webpage webpage = cache.get(url);
            if (webpage == null) {
                webpage = generator.readPage(url);
                Webpage out = cache.add(webpage);
                if (out!= null) {

                }
            }
        }
        long timeEnd = System.currentTimeMillis();
        String cacheInfo = cache.toString();
        cacheInfo += "\n----------------------------------------------------------------\n";
        cacheInfo += "Time elapsed: " + (float) (timeEnd - timeStart)  + " milliseconds\n";
        cacheInfo += "----------------------------------------------------------------\n";

        System.out.println(cacheInfo);
    }
}
