package brax.helpers;

import se.web.data.model.Store;

import java.util.Arrays;

public class BraxStoreProvider {
    public static final Store[] stores = {
            new Store("Luedenscheid.Sterncenter@Brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Lüdenscheid"),
            new Store("Hannover.EAG@Brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Hannover"),
            new Store("Erlangen.Store@Brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Erlangen"),
            new Store("NULL", "Qwe12345!", "MASTERMATE", "BRAX Store Düren"),
            new Store("Koeln.Rheincenter@Brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Köln-Weiden"),
            new Store("Koblenz.Loehr-Center@Brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Koblenz"),
            new Store("Muenchen.Pasing-Arcaden@Brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store München"),
            new Store("Bocholt.Store@Brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Bocholt"),
            new Store("Viernheim.RNZ@Brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Viernheim"),
            new Store("Landshut.Store@Brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Landshut"),
            new Store("Bochum.Store@Brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Bochum"),
            new Store("Bonn.Store@Brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Bonn"),
            new Store("Braunschweig.Store@Brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Braunschweig"),
            new Store("Passau.Store@Brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Passau"),
            new Store("Leverkusen.Store@brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Leverkusen"),
            new Store("Neu-Isenburg.Store@Brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Neu-Isenburg"),
            new Store("Hagen.Store@Brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Hagen"),
            new Store("Heidelberg.Store@brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Heidelberg"),
            new Store("Berlin.Store@Brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Berlin"),
            new Store("Augsburg.Store@brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Augsburg"),
            new Store("frankfurt.skyline@brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Skyline Plaza Frankfurt/Main"),
            new Store("regensburg.store@brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Regensburg"),
            new Store("Duesseldorf.Store@Brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Düsseldorf"),
            new Store("Lueneburg.Store@Brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Lüneburg"),
            new Store("Bielefeld.Store@Brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Bielefeld"),
            new Store("LeipzigHoefe.Store@brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Leipzig"),
            new Store("Rostock.Store@Brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Rostock"),
            new Store("Essen.Store@Brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Essen"),
            new Store("Potsdam.Store@Brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Potsdam"),
            new Store("Moenchengladbach.Store@Brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Mönchengladbach"),
            new Store("Koeln.Store@Brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Köln"),
            new Store("Dortmund.Store@Brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Dortmund"),
            new Store("flensburg.store@brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Flensburg"),
            new Store("muemchen.store@brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store München - Riem"),
            new Store("muenster.store@brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Münster"),
            new Store("aachen.store@brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Aachen"),
            new Store("stuttgart.store@brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Stuttgart"),
            new Store("salzburg.store@brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Salzburg"),
            new Store("linz.store@brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Pasching"),
            new Store("wien.store@brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Wien"),
            new Store("amstelveen.store@brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Amstelveen"),
            new Store("denhaag.store@brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Den Haag"),
            new Store("turnhout.store@brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Turnhout"),
            new Store("gent.store@brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Gent"),
            new Store("antwerpen.store@brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Antwerpen"),
            new Store("leuven.store@brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Leuven"),
            new Store("herentals.store@brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Herentals"),
            new Store("diest.store@brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Diest"),
            new Store("mechelen.store@brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Mechelen"),
            new Store("tongeren.store@brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Tongeren"),
            new Store("wijnegem.store@brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Wijnegem"),
            new Store("kortrijk.store@brax.com", "Qwe12345!", "MASTERMATE", "BRAX Store Kortrijk"),
            new Store("Hamburg.AEZ@Brax.com", "Qwe12345!", "MASTERMATE", "BraxStore Bertrange"),
            new Store("NULL", "Qwe12345!", "MASTERMATE", "BRAX Online-Shop")

    };

    public static Store getStoreByName(String storeName) {
        return Arrays.stream(stores).filter(store -> store.getName().equals(storeName)).findFirst().get();
    }
}
