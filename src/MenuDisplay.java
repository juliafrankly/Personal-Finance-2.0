class MenuDisplay {
    public void displayWelcome() {
        System.out.println("╔═══════════════════════════════════╗");
        System.out.println("║  Välkommen till din ekonomi-app!  ║");
        System.out.println("╚═══════════════════════════════════╝");
    }

    public void displayMainMenu() {
        System.out.println("\n○-○-○-○-○-○-○-○ HUVUDMENY ○-○-○-○-○-○-○-○");
        System.out.println("1. Lägg till en transaktion");
        System.out.println("2. Ta bort en transaktion");
        System.out.println("3. Visa alla transaktioner");
        System.out.println("4. Visa transaktioner efter period");
        System.out.println("5. Visa transaktioner efter typ");
        System.out.println("6. Visa nuvarande kontobalans");
        System.out.println("0. Avsluta");
        System.out.println("○-○-○-○-○-○-○-○-○-○-○-○-○-○-○-○-○-○-○-○-○");
        System.out.print("\nDitt val: ");
    }

    public void displayGoodbye() {
        System.out.println("\nTack för att du använde programmet!");
    }

    public void displayPeriodMenu() {
        System.out.println("\n~~~~~~~~~~ Visa efter period ~~~~~~~~~~");
        System.out.println("1. Dag");
        System.out.println("2. Vecka");
        System.out.println("3. Månad");
        System.out.println("4. År");
        System.out.println("0. Tillbaka");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.print("Välj period: ");
    }
}
