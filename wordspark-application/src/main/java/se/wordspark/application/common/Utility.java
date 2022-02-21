package se.wordspark.application.common;

public class Utility {

 /* public static <K, V extends Comparable<? super V>> Map<K, V> sortIncreaseByValue(Map<K, V> map) {
    List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
    list.sort(Entry.comparingByValue());

    Map<K, V> result = new LinkedHashMap<>();
    for (Entry<K, V> entry : list) {
      result.put(entry.getKey(), entry.getValue());
    }
    return result;
  }

  public static <K, V extends Comparable<? super V>> Map<K, V> sortDecreaseByValue(Map<K, V> map) {
    List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
    list.sort(Entry.comparingByValue());

    Map<K, V> result = new LinkedHashMap<>();
    for (int i = list.size() - 1; i >= 0; i--) {
      result.put(list.get(i).getKey(), list.get(i).getValue());
    }
    return result;
  }*/
}