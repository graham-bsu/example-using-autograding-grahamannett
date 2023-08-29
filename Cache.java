import java.util.LinkedList;



public class Cache<K, V extends KeyInterface<K>> {
    private LinkedList<V> cache;
    private int size;
    private int totalReferences = 0;
    private int totalHits = 0;

    /**
     * Constructor for Cache.
     *
     * @param size - size of the cache
     */
    public Cache(int size) {
        this.size = size;
        cache = new LinkedList<V>();
    }

    /*
     * get()
        Whenever an application requires a specific object, it searches/reads the cache using a key.  
        The cache hits when the key matches an object present in the cache, then returns the object 
        to the calling program and moves it to the first position in the cache (Most Recently Used 
        [MRU] scheme).  Alternatively, if the object is not present and the cache misses, the 
        returned value is null.
     */
    public V get(K key) {
        totalReferences++;
        for (V value : cache) {
            K temp = value.getKey();
            if (temp.equals(key)) {
                cache.remove(value);
                cache.addFirst(value);
                totalHits++;
                return value;
            }
        }
        return null;
    }

    /*
     * add()
        Adds an object to the first position of the cache. If the cache is full, the last entry (Least Recently Used (LRU) scheme) in the cache is removed before a new entry can be added. This is because the size of the cache is fixed. If an object is removed, it should be returned to the calling program, or null otherwise.
     */
    public V add(V value) {
        if (cache.size() == size) {
            V last = cache.removeLast();
            cache.addFirst(value);
            return last;
        }
        cache.addFirst(value);
        return null;
    }
    
    /*
     * remove()
        Removes an object from the cache. If the object is not present, the method returns null. Otherwise, the method returns the object.
     */

    public V remove(V value) {
        if (cache.contains(value)) {
            cache.remove(value);
            return value;
        }
        return null;
    }

    /*
     * clear()
        Clears the cache of all objects.
     */
    public void clear() {
        cache.clear();
    }

    /*
     * toString()
     * The toString for the cache outputs a string containing statistics about the cache: 
     * the number of entries in the cache; 
     * the number of references; number of hits; 
     * the hit ratio (as a percentage to two decimal points).  
     * 
     * Below is an example of the String output:
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                Cache with 10 entries has been created
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                Total number of references:        100
                Total number of cache hits:        74
                Cache hit percent:                 74.00%
     */

     public String toString() {

        String formattedPercent = String.format("%.02f", (((float) totalHits * 100.0)/ (float) totalReferences));
        String output = "";
         output += "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
         output += "Cache with " + size + " entries has been created\n";
         output += "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
         output += "Total number of references:        " + totalReferences + "\n";
         output += "Total number of cache hits:        " + totalHits + "\n";
         output += "Cache hit percent:                 " + formattedPercent + "%\n";

            return output;
     }
    
}
