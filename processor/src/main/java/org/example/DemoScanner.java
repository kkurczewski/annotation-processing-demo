package org.example;

import java.util.List;
import javax.lang.model.element.Element;
import javax.lang.model.util.ElementScanner14;

public class DemoScanner extends ElementScanner14<List<String>, List<String>> {
    @Override
    public List<String> scan(Element e, List<String> data) {
        data.add(e.toString());
        return data;
    }
}