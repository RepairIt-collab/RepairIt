var calendar = new SimpleCalendar('#cal', {
  // Options et configurations du calendrier
  months: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'],
  days: ['Dim', 'Lun', 'Mar', 'Mer', 'Jeu', 'Ven', 'Sam'],
  events: [
    {
      startDate: new Date('2023-11-09'),
      endDate: new Date('2023-11-10'),
      summary: 'Mon événement'
    }
  ]
});