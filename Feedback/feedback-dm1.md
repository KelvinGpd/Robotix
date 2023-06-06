# Commentaires DM1

Corrigé par An Li

Total: 96%

## Glossaire [8/10]

- Lister les types d'action
- Usage et historique se répètent un peu
- Robot
  - Vous voulez dire qu'il contient obligatoirement une composante autre que le CPU
- Un peu difficile à lire, pas d'espace entre les lignes, mettre de préférence le terme en **gras**

## Diagramme de cas utilisation [23/25]

- Il manque le robot comme acteur

## Description des cas d'utilisation [46/50]

- Créer une action
  - L'utilisateur doit entrer le type de l'action ainsi que ses spécifications propres au type d'action qu'il a choisi
- Un robot cesse de fonctionner / anomalies
  - Parfois, le système peut régler l'anomalie par lui-même, cette partie ne fait pas partie du scénario
- Parfois, les numéros ne se suivent pas
  - e.g., 2 → 4

## Risques [5/5]

Bien!

## Besoins non-fonctionnels [5/5]

Bien!

## Bonne utilisation de GitHub et statistiques [1/5]

- Pas de release créé: nous avons dû la créer
- Aucune utilisation d'issues ni de pull requests
  - Créez vos tâches dans l'onglet Issues et assignez-les aux membres de l'équipe
- Aucune utilisation de branches: tout est poussé dans main
- Bonne répartition de commits
- Pas d'Insights: nous allons évaluer cette partie dans le devoir 2

## Bonus: Application Java [8/10]

- Cas d'utilisation fonctionnelles:
  - S'inscrire
  - Enregistrer un robot
  - Acheter une composante (permet d'ajouter une seule composante avec nom)
  - Ajouter une composante à vendre (permet d'ajouter une composante avec le nom seulement)
  - Voir mes composantes
- Mais il n'y a pas de génération de JAR
