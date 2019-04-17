package fr.afcepf.al33.dto;

import lombok.*;

// DTO = Data Transfert Object
// Objet de données transférées à travers le réseau (via Rest ou Soap ou Rmi)
// données pas stockés dans la basse de données
// surtout pas de @Entity


//@ToString @Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Data
public class ResConv {

    private Double montant;
    private String source;
    private String cible;
    private Double montantConverti;
}
