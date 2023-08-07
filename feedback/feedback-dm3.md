# Commentaires DM3

Corrigé par An Li

Total: 82%

## Code source Java du programme [39/45]

- Généralement, le code source devrait suivre cette hiérarchie:
  - src/main/: Code source, excluant les tests
  - src/test/: Code source des tests
- Configuration de base non respectée
  - Seulement une activité est initialisée, aucun utilisateur ni fournisseur
- La logique du run dans UserView par example est peu intuitive/documentée, et, plus gravement des segments comme l'affichage dans le while(true) devraient être separés en plusieurs lignes avec variables/constantes pour faciliter la maintenance.

## Tests unitaires en JUnit [15/20]

- Nombre de tests insuffisants - Vous devez faire 12 tests (i.e., 3 tests par membre)
- La capture d'écran de JUnit ne s'affiche pas

## Configuration Maven et production du JAR [6/10]

- Placer les tests dans src/test/java pour que Maven puisse les exécuter
- Problèmes avec pom.xml
  - Point d'entrée non indiqué
  - Créer une version du JAR avec dépendances
  - junit-jupiter déclaré deux fois
  - Voir diapositives de la démo 9

## Manuel utilisateur (README) [4/5]

- Indiquer où se trouvent les données de départ

## JavaDocs générés [5/5]

Bien!

## Cohérence entre les modèles et le code [13/15]

- Le diagramme de classes ne s'affiche pas
- Certaines de vos classes ont trop de responsabilités (dont Utilisateur, qui gère logique allant des données de l'utilisateur, jusqu'aux actions et notifications)

## Bonus: Interface graphique [N/A]
