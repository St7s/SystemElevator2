================================== Systéme ascenseur =================================
Le système ascenseur implémente le partern Observer / Observable, pour lequelles 
l'interface utilisateur et le système de contrôle peuvent s'abonner à différents
événements.
Il en existe 3 :
	- notification d'un changement de niveau
	- notification d'un arret
	- notification d'une surcharge

=================================== Capteur Niveau ===================================

Les capteurs de niveaux sont en faite simuler par une classe "CapteurNiveau",
c'est à dire que cette instance teste pour chaque niveau si la position de la 
cabine correspond à un niveau. 

En réalité il aurait fallu faire une liste de 
capteur de niveau pour chaque niveau et que ceux-ci détecte individuellement 
si la cabine est en face d'eux. Mais ce système aurait nécessité beaucoup trop de code.





