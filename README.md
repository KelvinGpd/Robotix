# Robotix

## Description
Ce projet a été fait pour le cours IFT2255 à UdeM. Nous avons utilisé Java pour l'implémenter. L'application permet à des utilisateurs de créer, gérer et controller son 
propre flot de robots, et à des fournisseurs de vendre aux utilisateurs ses composantes et ses robots. Nous avons implémenté une interface en ligne de commande.
Les données de l'application sont stockés dans des .json, pour qu'elles puissent persister après chaque fois qu'un utilisateur quitte l'application. Par exemple, si un 
nouveau compte est créé, ce compte sera reconnu même si l'application est fermée.

## Démarrage

1. Assurez-vous d'avoir Java installé sur votre système. Si ce n'est pas le cas, téléchargez et installez Java depuis le site officiel d'Oracle.
2. Téléchargez le fichier JAR (Java Archive) du projet depuis notre dépôt GitHub ou le lien fourni.
3. Naviguez jusqu'au répertoire où vous avez enregistré le fichier JAR à l'aide de la commande cd (change directory).
4. Pour démarrer l'application, vous pouvez aller dans Implementation/out/artifacts/RobotixApp et cliquer sur RobotixApp.jar.
5. Si cela ne marche pas, vous pouvez aller dans Implementation/Robotik_Backend/target et cliquer sur Prototype_Java-1.0-SNAPSHOT.jar.
6. Vous pouvez egalement lancer lapplication a partir de l'invite de commande

## Utilisation

Une fois l'application démarrée, elle vous présentera des invites (ou des questions) pour que vous puissiez interagir avec elle. Vous devrez fournir des réponses aux questions en suivant le format requis. Assurez-vous de saisir les informations correctes pour que l'application fonctionne correctement.

L'application vous permet de gérer les activités, les tâches et les composants de vos robots. Vous pouvez ajouter, modifier ou supprimer des activités et des tâches, ainsi que gérer les composants nécessaires pour chaque tâche.

Vous avez toujours l'option de revenir sur vos pas, et des qu'un action de modification est faite, elle est sauvegardee.

Profitez de l'expérience de gestion efficace de vos robots en utilisant cette application conviviale !

### En tant que vendeur

Assurez vous d'avoir une bonne description de votre projet afin d'avoir les meilleures ventes !

### Pour les devs

Pour extend les fonctionnalites, juste ajouter une option dans le case, et associer votre methode qui prend les inputs a ce case.
Pour extendre l'information prise du database ou la modifier, simplement changer les constructeurs et ajouter les getters/setters.
Referer au javadoc !


