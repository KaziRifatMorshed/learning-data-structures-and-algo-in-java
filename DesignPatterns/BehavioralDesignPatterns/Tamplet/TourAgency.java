package DesignPatterns.BehavioralDesignPatterns.Tamplet;

/*
This alternate is also usable
//abstract class Tour{
//    public abstract void doComingTransport();
//    public abstract void doDayA();
//    public abstract void doDayB();
//    public abstract void doDayC();
//    public abstract void doReturningTransport();
//}
//
//abstract class Trip extends Tour{
//    public final void performTrip() {
//        doComingTransport();
//        doDayA();
//        doDayB();
//        doDayC();
//        doReturningTransport();
//    }
//}
*/
abstract class Trip {
    public final void performTrip() {
        doComingTransport();
        doDayA();
        doDayB();
        doDayC();
        doReturningTransport();
    }

    public abstract void doComingTransport();

    public abstract void doDayA();

    public abstract void doDayB();

    public abstract void doDayC();

    public abstract void doReturningTransport();

}

class PackageA extends Trip {
    public void doComingTransport() {
        System.out.println("The tourists are coming by air ...");
    }

    public void doDayA() {
        System.out.println("The tourists are visiting the aquarium ...");
    }

    public void doDayB() {
        System.out.println("The tourists are going to the beach ...");
    }

    public void doDayC() {
        System.out.println("The tourists are going to mountains ...");
    }

    public void doReturningTransport() {
        System.out.println("The tourists are going home by air ...");
    }
}

class PackageB extends Trip {
    public void doComingTransport() {
        System.out.println("The tourists are coming by train ...");
    }

    public void doDayA() {
        System.out.println("The tourists are visiting the mountain ...");
    }

    public void doDayB() {
        System.out.println("The tourists are going to the beach ...");
    }

    public void doDayC() {
        System.out.println("The tourists are going to zoo ...");
    }

    public void doReturningTransport() {
        System.out.println("The tourists are going home by train ...");
    }
}


class TourAgency {
    public static void main(String[] args) {
        Trip aTrip = new PackageA();
        aTrip.performTrip();

        aTrip = new PackageB();
        aTrip.performTrip();
    }
}

