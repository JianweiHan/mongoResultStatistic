import com.mongodb.*;

public class Main {

    public static void main(String[] args) {
        DB dB=(new MongoClient(new MongoClientURI("mongodb://172.31.26.123:27017, 172.31.13.18:27017, 172.31.3.93:27017, 172.31.10.34:27017, 172.31.25.76:27017"))).getDB("test");
        DBCollection dBCollection = dB.getCollection("test");

        DB dBlocal=(new MongoClient("localhost",27017)).getDB("test");
        DBCollection dBCollectionlocal = dBlocal.getCollection("test");

        long surviors = dBCollection.count();
        long total = dBCollectionlocal.count();
        long acknowledged=dBCollectionlocal.find(new BasicDBObject("acknowledged", true)).count();
        long loss = acknowledged - surviors;
        long unack = surviors-acknowledged;
        double ackRate = (acknowledged*1.0)/total;
        double lssRate = (loss*1.0)/total;
        double unackSuccess = (unack*1.0)/surviors;

        System.out.println(total + " total");
        System.out.println(acknowledged + " acknowledged");
        System.out.println(surviors + " surviors");
        System.out.println(loss+ " acknowledged writes lost");
        System.out.println(Double.toString(ackRate)+ " ack rate");
        System.out.println(Double.toString(lssRate)+ " loss rate");
        System.out.println(Double.toString(unackSuccess)+" unacknowledged but successful rate");

        //long acknowledged=dBCollectionlocal.find(new BasicDBObject("number", new BasicDBObject("$gt", 3))).count();
        //System.out.println("count   "+ total +" number"+ acknowledged);


    }
}
