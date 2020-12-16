package com.my.mytest;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class JAXBTest {

    public static void main(String[] args) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Boy.class);

        Marshaller marshaller = context.createMarshaller();
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Boy boy = new Boy();
        marshaller.marshal(boy, System.out);
        System.out.println();

        String xml = "<boy><name>David</name></boy>";
        Boy boy2 = (Boy) unmarshaller.unmarshal(new StringReader(xml));
        System.out.println(boy2.name);
    }

}
