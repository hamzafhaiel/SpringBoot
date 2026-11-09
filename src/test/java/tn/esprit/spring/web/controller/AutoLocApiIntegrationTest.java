package tn.esprit.spring.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "spring.datasource.url=jdbc:h2:mem:autoloc-test;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "server.servlet.context-path="
})
class AutoLocApiIntegrationTest {

    @LocalServerPort
    private int port;

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Test
    void swaggerAndCrudEndpointsAreAvailable() throws Exception {
        HttpResponse<String> swaggerUi = send("GET", "/swagger-ui/index.html", null);
        assertThat(swaggerUi.statusCode()).isEqualTo(200);

        HttpResponse<String> openApi = send("GET", "/v3/api-docs", null);
        assertThat(openApi.statusCode()).isEqualTo(200);
        assertThat(openApi.body()).contains("/employes", "/vehicules", "/reservations",
                "/employes/affecterAgence/{idAgence}/{idEmploye}",
                "/vehicules/affecterEquipement/{idv}/{ideq}",
                "/maintenances/affecterMaintenanceVehicule/{idm}/{idV}");

        for (String path : new String[]{
                "/agences/listall", "/clients/listall", "/contrats/listall",
                "/employes/listall", "/equipements/listall", "/maintenances/listall",
                "/paiements/listall", "/reservations/listall", "/vehicules/listall"
        }) {
            HttpResponse<String> response = send("GET", path, null);
            assertThat(response.statusCode()).as(path).isEqualTo(200);
            assertThat(response.body()).as(path).isEqualTo("[]");
        }

        HttpResponse<String> clientWithReservations = send("POST", "/clients/ajouterReservationClient", """
                {
                  "nom": "Client cascade", "prenom": "Test",
                  "reservations": [{
                    "dateDebut": "2026-11-01", "dateFin": "2026-11-03", "statut": "EN_ATTENTE"
                  }]
                }
                """);
        assertThat(clientWithReservations.statusCode()).isEqualTo(201);
        Matcher reservationIdMatcher = Pattern.compile("\"idReservation\"\\s*:\\s*(\\d+)")
                .matcher(clientWithReservations.body());
        assertThat(reservationIdMatcher.find()).isTrue();
        HttpResponse<String> savedReservation = send("GET", "/reservations/" + reservationIdMatcher.group(1), null);
        assertThat(savedReservation.statusCode()).isEqualTo(200);
        assertThat(savedReservation.body()).contains("EN_ATTENTE");
        Matcher clientIdMatcher = Pattern.compile("\"idClient\"\\s*:\\s*(\\d+)")
                .matcher(clientWithReservations.body());
        assertThat(clientIdMatcher.find()).isTrue();
        HttpResponse<String> savedClient = send("GET", "/clients/" + clientIdMatcher.group(1), null);
        assertThat(savedClient.statusCode()).isEqualTo(200);
        assertThat(savedClient.body()).contains("\"idReservation\":" + reservationIdMatcher.group(1));

        HttpResponse<String> createdPaiement = send("POST", "/paiements", """
                {"montant":150,"datePaiement":"2026-11-09","modePaiement":"CARTE"}
                """);
        assertThat(createdPaiement.statusCode()).isEqualTo(201);
        Matcher paiementIdMatcher = Pattern.compile("\"idPaiement\"\\s*:\\s*(\\d+)")
                .matcher(createdPaiement.body());
        assertThat(paiementIdMatcher.find()).isTrue();
        String paiementId = paiementIdMatcher.group(1);

        HttpResponse<String> reservationWithContrat = send("POST", "/reservations/ajouterReservationContrat", """
                {
                  "dateDebut": "2026-11-04", "dateFin": "2026-11-06", "statut": "CONFIRMEE",
                  "contrat": {
                    "dateSignature": "2026-10-30", "montantTotal": 150, "valide": true,
                    "paiements": [{"idPaiement": %s}]
                  }
                }
                """.formatted(paiementId));
        assertThat(reservationWithContrat.statusCode()).isEqualTo(201);
        Matcher contratIdMatcher = Pattern.compile("\"idContrat\"\\s*:\\s*(\\d+)")
                .matcher(reservationWithContrat.body());
        assertThat(contratIdMatcher.find()).isTrue();
        HttpResponse<String> savedContrat = send("GET", "/contrats/" + contratIdMatcher.group(1), null);
        assertThat(savedContrat.statusCode()).isEqualTo(200);
        assertThat(savedContrat.body()).contains("\"valide\":true");
        assertThat(savedContrat.body()).contains("\"idPaiement\":" + paiementId);
        Matcher linkedReservationIdMatcher = Pattern.compile("\"idReservation\"\\s*:\\s*(\\d+)")
                .matcher(reservationWithContrat.body());
        assertThat(linkedReservationIdMatcher.find()).isTrue();
        HttpResponse<String> linkedReservation = send("GET", "/reservations/" + linkedReservationIdMatcher.group(1), null);
        assertThat(linkedReservation.statusCode()).isEqualTo(200);
        assertThat(linkedReservation.body()).contains("\"idContrat\":" + contratIdMatcher.group(1));

        HttpResponse<String> deletedReservation = send("DELETE",
                "/reservations/" + linkedReservationIdMatcher.group(1), null);
        assertThat(deletedReservation.statusCode()).isEqualTo(204);
        assertThat(send("GET", "/contrats/" + contratIdMatcher.group(1), null).statusCode()).isEqualTo(404);
        assertThat(send("GET", "/paiements/" + paiementId, null).statusCode()).isEqualTo(200);

        HttpResponse<String> created = send("POST", "/employes",
                "{\"nom\":\"Hamza\",\"prenom\":\"Fhaiel\",\"role\":\"AGENT\"}");
        assertThat(created.statusCode()).isEqualTo(201);
        Matcher idMatcher = Pattern.compile("\"idEmploye\"\\s*:\\s*(\\d+)").matcher(created.body());
        assertThat(idMatcher.find()).isTrue();
        String id = idMatcher.group(1);

        HttpResponse<String> createdAgence = send("POST", "/agences",
                "{\"nom\":\"Agence test\",\"ville\":\"Tunis\",\"adresse\":\"Centre\",\"telephone\":\"71000000\"}");
        assertThat(createdAgence.statusCode()).isEqualTo(201);
        Matcher agenceIdMatcher = Pattern.compile("\"idAgence\"\\s*:\\s*(\\d+)").matcher(createdAgence.body());
        assertThat(agenceIdMatcher.find()).isTrue();
        String agenceId = agenceIdMatcher.group(1);

        HttpResponse<String> affected = send("PUT",
                "/employes/affecterAgence/" + agenceId + "/" + id, null);
        assertThat(affected.statusCode()).isEqualTo(200);
        assertThat(affected.body()).contains("Agence test");

        HttpResponse<String> createdVehicule = send("POST", "/vehicules",
                "{\"immatriculation\":\"TEST-001\",\"marque\":\"Toyota\",\"modele\":\"Yaris\","
                        + "\"categorie\":\"CITADINE\",\"tarifJournalier\":50.00,\"statut\":\"DISPONIBLE\"}");
        assertThat(createdVehicule.statusCode()).isEqualTo(201);
        Matcher vehiculeIdMatcher = Pattern.compile("\"idVehicule\"\\s*:\\s*(\\d+)").matcher(createdVehicule.body());
        assertThat(vehiculeIdMatcher.find()).isTrue();
        String vehiculeId = vehiculeIdMatcher.group(1);

        HttpResponse<String> createdEquipement = send("POST", "/equipements", "{\"libelle\":\"GPS\"}");
        assertThat(createdEquipement.statusCode()).isEqualTo(201);
        Matcher equipementIdMatcher = Pattern.compile("\"idEquipement\"\\s*:\\s*(\\d+)")
                .matcher(createdEquipement.body());
        assertThat(equipementIdMatcher.find()).isTrue();

        HttpResponse<String> vehiculeWithEquipement = send("PUT",
                "/vehicules/affecterEquipement/" + vehiculeId + "/" + equipementIdMatcher.group(1), null);
        assertThat(vehiculeWithEquipement.statusCode()).isEqualTo(200);
        assertThat(vehiculeWithEquipement.body()).contains("GPS");

        HttpResponse<String> createdMaintenance = send("POST", "/maintenances",
                "{\"dateDebut\":\"2026-10-30\",\"dateFin\":\"2026-10-31\",\"description\":\"Revision\"}");
        assertThat(createdMaintenance.statusCode()).isEqualTo(201);
        Matcher maintenanceIdMatcher = Pattern.compile("\"idMaintenance\"\\s*:\\s*(\\d+)")
                .matcher(createdMaintenance.body());
        assertThat(maintenanceIdMatcher.find()).isTrue();

        HttpResponse<String> maintenanceWithVehicule = send("PUT",
                "/maintenances/affecterMaintenanceVehicule/" + maintenanceIdMatcher.group(1) + "/" + vehiculeId,
                null);
        assertThat(maintenanceWithVehicule.statusCode()).isEqualTo(200);
        assertThat(maintenanceWithVehicule.body()).contains("TEST-001");

        HttpResponse<String> updated = send("PUT", "/employes/" + id,
                "{\"nom\":\"Hamza\",\"prenom\":\"Updated\",\"role\":\"MANAGER\"}");
        assertThat(updated.statusCode()).isEqualTo(200);
        assertThat(updated.body()).contains("Updated", "MANAGER");

        HttpResponse<String> deleted = send("DELETE", "/employes/" + id, null);
        assertThat(deleted.statusCode()).isEqualTo(204);

        HttpResponse<String> missing = send("GET", "/employes/" + id, null);
        assertThat(missing.statusCode()).isEqualTo(404);
    }

    private HttpResponse<String> send(String method, String path, String body) throws Exception {
        HttpRequest.Builder request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + port + path));
        if (body == null) {
            request.method(method, HttpRequest.BodyPublishers.noBody());
        } else {
            request.header("Content-Type", "application/json")
                    .method(method, HttpRequest.BodyPublishers.ofString(body));
        }
        return httpClient.send(request.build(), HttpResponse.BodyHandlers.ofString());
    }
}
