La configuration des instructions est définie au niveau des tests automatisés. Au lieu de l’être dans un fichier en entrée.

Chaque tendeuse doit connaître au préalable la dimension de la pelouse, sa position de départ ainsi que ses instructions.
Le controller a une vue sur toutes les tendeuses.A noter que le pattern "observer" aurait pu être une solution, aussi.
Le rôle du controller s'arrête au lancement de la tendeuse dans une position non occupée.
Par la suite c'est à la tendeuse d'assurer ses coups(éviter les collisions et les sorties de piste). 
Aucune exception n'est lévée/traitée. Ceci est remplacé par l'enregistrement des incidents survenus au cours des parcours.

L'AOP n'est utilisé que pour injecter un timer entre les instructions afin de les voir dérouler un par un. 
Remarque j'aurais pu utiliser la même technique pour les logs.
Le ICallBack#endTreatment est utilisé afin d'isoler les traitements post instructions.

Les logs ont deux sorties : 
	°Terminal
	°Fichier : mower.log
Pour lancer l'application : mvn test ou mvn clean install.
