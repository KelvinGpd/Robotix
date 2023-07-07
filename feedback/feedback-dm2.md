# Commentaires DM2

Corrigé par Santiago Gomez Maqueo Anaya

Total:

## Description du système opérationnel [5/5]
Excellent. Vous avez satisfait tous les critères attendus.

## 6 diagrammes d'activité UML [20/25]
Attention!!!! Il y a des fautes de frappe (ou caractères spéciaux) dans vos noms des fichiers, et donc vos images ne s'affichent pas correctement!!!
Aussi, le diagramme de Modifier/ajouter une tache sequence n'est pas un diagramme d'activité, mais plutôt un de séquence.

## Diagramme de classes UML [18/20]
Très -très- bon travail, mais vous beneficierais d'une décomposition plus atomique. Certaines de vos classes ont trop de responsabilités (dont Utilisateur, qui gère logique allant des données de l'utilisateur, jusqu'aux actions et notifications). De même, l'utilisation de composition avec User.java ne me paraît pas du tout intuitive, il me semblerait suffisant de juste mettre des associations simples.

## 5 diagrammes de séquence UML [30/30]
Excellent.

## Code source Java du programme et fichier JAR [12/20]
Attention! Si un ussager rentre un string non-numérique dans votre input des composants vous avez un NumberFormatException qui n'est pas traité, et qui fait crasher votre programme.

La logique du run dans UserView par example est peu intuitive/documentée, et, plus gravement des segments comme l'affichage dans le while(true) devraient être separés en plusieurs lignes avec variables/constantes pour faciliter la maintenance.
