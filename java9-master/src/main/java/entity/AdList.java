package entity;

import java.util.HashSet;

public class AdList extends ListOfIdentifiables<Ad> {

    private static final long serialVersionUID = 882150501461356499L;

    public synchronized Ad addAd(User author, Ad ad){
        ad.setAuthorId(author.getId());
        ad.setAuthor(author);
        ad.setId(getNextId());

        items.add(ad);
        return ad;
    }

    public synchronized void updateAd(Ad ad){
        items.add(ad);
    }

    public synchronized void deleteAd(Ad ad){
        items.remove(ad);
    }

    public synchronized HashSet<Ad>getAds(){
        return (HashSet<Ad>)items.clone();
    }

}