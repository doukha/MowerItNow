J'ai opté pour une configuration au niveau des tests automatisés plutôt que le parsing d'un fichier. 
J'ai supposé que chaque tendeuse doit connaitre au préalable la pelouse, sa position de départ ainsi que ses instructions. 
La contrainte de déplacement séquentielle est géré implicitement dans la boucle for. Pas besoin de call back ou de pattern
oberver.
Le controller c'est lui qui met la tendeuse dans une zone non occupé. ensuite c'est à la tendeuse de vérifier qu'elle ne rentrer pas en collision avec
les autres tendeuses. 
Pas d'exceptions levées et par conséquent pas d'exceptions traitées.
//Ajouter de l'aop pour injecter un timer entre les méthodes histoire de faire vraie.
//pattern observer : communication entre plusieurs tendeuse
//un callback.
//debug step by step
//Clean code/ commentary
Pas le temps de filter les log dans 2 fichiers différents.
essayer en console sur mac pour voir les logs.
//Pousser tout ça sur github

assert avec eclipse il ne l'a detecte pas mais avec maven il la détecte
aspectj ne marche pas avec maven pas de log en sortie