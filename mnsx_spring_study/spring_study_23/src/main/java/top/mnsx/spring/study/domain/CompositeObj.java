package top.mnsx.spring.study.domain;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CompositeObj {
    private List<String> stringList;
    private Set<String> stringSet;
    private Map<String, String> stringMap;

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public Set<String> getStringSet() {
        return stringSet;
    }

    public void setStringSet(Set<String> stringSet) {
        this.stringSet = stringSet;
    }

    public Map<String, String> getStringMap() {
        return stringMap;
    }

    public void setStringMap(Map<String, String> stringMap) {
        this.stringMap = stringMap;
    }

    @Override
    public String toString() {
        return "CompositeObj{" +
                "stringList=" + stringList +
                ", stringSet=" + stringSet +
                ", stringMap=" + stringMap +
                '}';
    }
}
