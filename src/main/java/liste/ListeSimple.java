package liste;

public class ListeSimple {
    private long size;
    Noeud tete;

    /**
     * Retourne la taille actuelle de la liste.
     * @return La taille de la liste.
     */
    public long getSize() {
        return size;
    }

    /**
     * Ajoute un élément au début de la liste.
     * @param element L'élément à ajouter.
     */
    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }

    /**
     * Modifie le premier noeud contenant l'élément spécifié.
     * @param element L'élément à chercher dans la liste.
     * @param nouvelleValeur La nouvelle valeur à attribuer au premier noeud trouvé.
     */
    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null && courant.getElement() != element)
            courant = courant.getSuivant();
        if (courant != null)
            courant.setElement(nouvelleValeur);
    }

    /**
     * Modifie tous les noeuds contenant l'élément spécifié.
     * @param element L'élément à chercher dans la liste.
     * @param nouvelleValeur La nouvelle valeur à attribuer aux noeuds trouvés.
     */
    public void modifieTous(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null) {
            if (courant.getElement() == element)
                courant.setElement(nouvelleValeur);
            courant = courant.getSuivant();
        }
    }

    /**
     * Retourne une chaîne de caractères représentant la liste sous la forme "ListeSimple(Noeud(...))".
     * @return La représentation en chaîne de la liste.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("ListeSimple(");
        Noeud n = tete;
        while (n != null) {
            sb.append(n);
            n = n.getSuivant();
            if (n != null)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Supprime le premier noeud contenant l'élément spécifié.
     * @param element L'élément à chercher pour suppression.
     */
    public void supprimePremier(Object element) {
        if (tete != null) {
            if (tete.getElement() == element) {
                tete = tete.getSuivant();
                size--;
                return;
            }
            Noeud precedent = tete;
            Noeud courant = tete.getSuivant();
            while (courant != null && courant.getElement() != element) {
                precedent = precedent.getSuivant();
                courant = courant.getSuivant();
            }
            if (courant != null) {
                precedent.setSuivant(courant.getSuivant());
                size--;
            }
        }
    }

    /**
     * Supprime tous les noeuds contenant l'élément spécifié.
     * @param element L'élément à supprimer.
     */
    public void supprimeTous(int element) {
       tete = supprimeTousRecurs(element, tete);
    }

    /**
     * Supprime tous les noeuds contenant l'élément spécifié de manière récursive.
     * @param element L'élément à supprimer.
     * @param tete Le noeud à partir duquel commencer la suppression.
     * @return Le nouveau début de la liste après suppression.
     */
    public Noeud supprimeTousRecurs(Object element, Noeud tete) {
        if (tete != null) {
            Noeud suiteListe = supprimeTousRecurs(element, tete.getSuivant());
            if (tete.getElement() == element) {
                size--;
                return suiteListe;
            } else {
                tete.setSuivant(suiteListe);
                return tete;
            }
        } else return null;
    }

    /**
     * Retourne le noeud avant le dernier dans la liste.
     * @return Le noeud avant le dernier, ou null si la liste est vide ou n'a qu'un seul élément.
     */
    public Noeud getAvantDernier() {
        if (tete == null || tete.getSuivant() == null)
            return null;
        else {
            Noeud courant = tete;
            Noeud suivant = courant.getSuivant();
            while (suivant.getSuivant() != null) {
                courant = suivant;
                suivant = suivant.getSuivant();
            }
            return courant;
        }
    }

    /**
     * Inverse l'ordre des noeuds dans la liste.
     */
    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;
        while (courant != null) {
            Noeud next = courant.getSuivant();
            courant.setSuivant(precedent);
            precedent = courant;
            courant = next;
        }
        tete = precedent;
    }

    /**
     * Retourne le noeud précédant un noeud spécifié.
     * @param r Le noeud dont on veut le précédent.
     * @return Le noeud précédant, ou null si le noeud est la tête.
     */
    public Noeud getPrecedent(Noeud r) {
        Noeud precedent = tete;
        Noeud courant = precedent.getSuivant();
        while (courant != r) {
            precedent = courant;
            courant = courant.getSuivant();
        }
        return precedent;
    }

    /**
     * Échange la position de deux noeuds spécifiés dans la liste.
     * @param r1 Le premier noeud à échanger.
     * @param r2 Le deuxième noeud à échanger.
     */
    public void echanger(Noeud r1, Noeud r2) {
        if (r1 != r2){
            Noeud precedentR1;
            Noeud precedentR2;
            if (r1 != tete && r2 != tete) {
                precedentR1 = getPrecedent(r1);
                precedentR2 = getPrecedent(r2);
                precedentR1.setSuivant(r2);
                precedentR2.setSuivant(r1);
            } else if (r1 == tete) {
                precedentR2 = getPrecedent(r2);
                precedentR2.setSuivant(tete);
                tete = r2;
            }
            else {
                precedentR1 = getPrecedent(r1);
                precedentR1.setSuivant(tete);
                tete = r1;
            }
            Noeud temp = r2.getSuivant();
            r2.setSuivant(r1.getSuivant());
            r1.setSuivant(temp);
        }
    }

}
