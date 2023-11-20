package be.uantwerpen.fti.ei.gamelogic;

import be.uantwerpen.fti.ei.gamelogic.Entities.*;
import be.uantwerpen.fti.ei.gamelogic.Entities.ScreenEntities.GameOverScreenEntity;
import be.uantwerpen.fti.ei.gamelogic.Entities.ScreenEntities.InformationOutputEntity;
import be.uantwerpen.fti.ei.gamelogic.Entities.ScreenEntities.StartScreenEntity;
import be.uantwerpen.fti.ei.gamelogic.Helpers.Constants;
import be.uantwerpen.fti.ei.gamelogic.Inputs.*;
import be.uantwerpen.fti.ei.gamelogic.Helpers.Stopwatch;
import be.uantwerpen.fti.ei.gamelogic.Movement.CollisionSystem;
import be.uantwerpen.fti.ei.gamelogic.Movement.MovementComponent;
import be.uantwerpen.fti.ei.gamelogic.Movement.MovementSystem;
import be.uantwerpen.fti.ei.gamelogic.Visuals.AFact;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Game {
    private final double GameCellsX = 72;
    private final double GameCellsY = 72;

    private AFact f = null;

    private boolean isRunning;
    private boolean isPaused;

    private boolean start;
    private final boolean waitingForStart = true;

    private int level = 1;
    private int amountOfPlayers = 1;
    private double projectileCounter;
    private double timeBetweenProjectiles;
    private double timeBonusSpawn;
    private double durationBonus;
    private boolean stillMoreBullets = false;

    private final MovementSystem updaterMS = new MovementSystem();
    private final CollisionSystem updaterCS = new CollisionSystem();
    private final List<MovementComponent> componentList = new ArrayList<>();
    private final List<Entity> allEntities = new ArrayList<>();

    private final Stopwatch stopwatch = new Stopwatch();

//Functies en methods---------------------------------------------------------------------------------------------------

    /**
     * assigns the abstract factory to the game
     * <p>
     * a simple constructor that takes the abstract factory and assigns it to the game
     *
     * @param f abstract factory
     */
    public Game(AFact f) {
        this.f = f;
    }

    /**
     * Initializes the game
     * <p>
     * initializes the game dimension, that the game is running, creates the player, creates the level and sets the stopwatch
     *
     * @throws IOException
     */
    public void Init() throws IOException {
        Constants.loadProperties();

        isRunning = true;
        createPlayer(amountOfPlayers);

        createLevel(1);

        createScoreOverlay();

        stopwatch.setTime();
    }

    //creation entities------------------------------------------------

    /**
     * creates bullets + checks player inputs
     */
    public void checkInput() {
        List<Entity> bulletList = new ArrayList<>();
        for (Entity e : allEntities) {
            if (e instanceof PlayerEntity playerEntity) {
                MovementComponent MC = playerEntity.getMovementComponent();
                MC.setVelocity(new double[] {playerEntity.getInputs().speedRight - playerEntity.getInputs().speedLeft, 0});

                //checks if the game is paused by pressing P------------------------------------------------------------
                isPaused = playerEntity.getInputs().isPaused;

                //creates the bullets whenever you press space----------------------------------------------------------
                if (playerEntity.getInputs().isShoot && playerEntity.getCooldown() < 0) {

                    PlayerBulletEntity bullet = f.createPlayerBullet(new MovementComponent(
                            0,
                            -1 * Double.parseDouble(Constants.getConstants().getProperty("BULLET_SPEED")),
                            playerEntity.getMovementComponent().getPosition()[0] + playerEntity.getMovementComponent().getEntitySize()[0] / 2,
                            playerEntity.getMovementComponent().getPosition()[1],
                            GameCellsX / 50,
                            GameCellsY / 50));
                    bulletList.add(bullet);
                    playerEntity.setCooldown(Double.parseDouble(Constants.getConstants().getProperty("PLAYER_COOLDOWN")));

                    if (stillMoreBullets) {
                        PlayerBulletEntity bulletExtraL = f.createPlayerBullet(new MovementComponent(
                                -1 * Double.parseDouble(Constants.getConstants().getProperty("BULLET_SPEED")),
                                -1 * Double.parseDouble(Constants.getConstants().getProperty("BULLET_SPEED")),
                                playerEntity.getMovementComponent().getPosition()[0] + playerEntity.getMovementComponent().getEntitySize()[0] / 2,
                                playerEntity.getMovementComponent().getPosition()[1],
                                GameCellsX / 50,
                                GameCellsY / 50));
                        PlayerBulletEntity bulletExtraR = f.createPlayerBullet(new MovementComponent(
                                Double.parseDouble(Constants.getConstants().getProperty("BULLET_SPEED")),
                                -1 * Double.parseDouble(Constants.getConstants().getProperty("BULLET_SPEED")),
                                playerEntity.getMovementComponent().getPosition()[0] + playerEntity.getMovementComponent().getEntitySize()[0] / 2,
                                playerEntity.getMovementComponent().getPosition()[1],
                                GameCellsX / 50,
                                GameCellsY / 50));
                        bulletList.add(bulletExtraL);
                        bulletList.add(bulletExtraR);
                    }
                } else {
                    playerEntity.setCooldown(playerEntity.getCooldown() - stopwatch.getDeltaTime());
                }
            }
        }
        allEntities.addAll(bulletList);
    }

    /**
     * creates player
     * @throws IOException
     */
    public void createPlayer(int amountOfPlayers) throws IOException {
        // players
        if (amountOfPlayers == 1) {
            double x1 = GameCellsX / 2;
            double y = GameCellsY - GameCellsY / 6;
            allEntities.add(f.createPlayer(new KeyboardInputs1(f.frame), new MovementComponent(0, 0, x1, y, GameCellsX / 15, GameCellsY / 15)));
        } else if (amountOfPlayers == 2) {
            double x1 = GameCellsX / 3;
            double x2 = 2*GameCellsX / 3;
            double y = GameCellsY - GameCellsY / 6;
            allEntities.add(f.createPlayer(new KeyboardInputs1(f.frame), new MovementComponent(0, 0, x1, y, GameCellsX / 15, GameCellsY / 15)));
            allEntities.add(f.createPlayer(new KeyboardInputs2(f.frame), new MovementComponent(0, 0, x2, y, GameCellsX / 15, GameCellsY / 15)));
        }
    }

    /**
     * creates the enemies based on the level
     * @param level
     * @throws IOException
     */
    public void createEnemy(int level) throws IOException {
        switch (level) {
            case 1 -> {

                this.timeBetweenProjectiles = Double.parseDouble(Constants.getConstants().getProperty("TIME_BETWEEN_BULLETS_LVL1"));
                int row = 0;
                int distance = 0;

                for (int i = 0; i < 15; i++) {
                    EnemyEntity newEnemy = f.createEnemy(new MovementComponent(Double.parseDouble(Constants.getConstants().getProperty("ENEMY_SPEED_LVL1")), 0, distance * GameCellsX / 10 + 1, row * GameCellsY / 10 +1, GameCellsX / 15, GameCellsY / 15));
                    if (distance < 5)
                        distance += 1;
                    if (i == 4 || i == 9) {
                        row += 1;
                        distance = 0;
                    }
                    allEntities.add(newEnemy);
                }

            }
            case 2 -> {
                this.timeBetweenProjectiles = Double.parseDouble(Constants.getConstants().getProperty("TIME_BETWEEN_BULLETS_LVL2"));
                int row = 0;
                int distance = 0;
                for (int i = 0; i < 21; i++) {
                    EnemyEntity newEnemy = f.createEnemy(new MovementComponent(Double.parseDouble(Constants.getConstants().getProperty("ENEMY_SPEED_LVL2")), 0, distance * GameCellsX / 10 + 1, row * GameCellsY / 10 +1, GameCellsX / 15, GameCellsY / 15));
                    if (distance < 7)
                        distance += 1;
                    if (i == 6 || i == 13) {
                        row += 1;
                        distance = 0;
                    }
                    allEntities.add(newEnemy);
                }
            }
            default -> {
                this.timeBetweenProjectiles = Double.parseDouble(Constants.getConstants().getProperty("TIME_BETWEEN_BULLETS_LVLDEFAULT"));
                int row = 0;
                int distance = 0;
                for (int i = 0; i < 32; i++) {
                    EnemyEntity newEnemy = f.createEnemy(new MovementComponent(Double.parseDouble(Constants.getConstants().getProperty("ENEMY_SPEED_LVLDEFAULT")), 0, distance * GameCellsX / 10 + 1, row * GameCellsY / 10 +1 , GameCellsX / 20, GameCellsY / 20));
                    if (distance < 8)
                        distance += 1;
                    if (i == 7 || i == 15 || i == 23) {
                        row += 1;
                        distance = 0;
                    }
                    allEntities.add(newEnemy);
                }
            }
        }
    }

    /**
     * creates the projectiles
     * <p>
     * checks through all the entities and makes a list of enemies, then chooses a random enemy out of the newly made list and spawns a projectile
     * @param EL entity list
     */
    public void createEnemyProjectile(List<Entity> EL) {
        List<EnemyProjectileEntity> projectileList = new ArrayList<>();
        List<Entity> enemyList = new ArrayList<>();
        for (Entity e : EL) {
            if (e instanceof EnemyEntity) {
                enemyList.add(e);
            }
        }
        Random rand = new Random();
        int upperbound = enemyList.size();
        int random = rand.nextInt(upperbound);
        //System.out.println(random);
        EnemyProjectileEntity projectile = f.createEnemyProjectile(
                new MovementComponent(0, Double.parseDouble(Constants.getConstants().getProperty("ENEMY_PROJECTILE_SPEED")),
                enemyList.get(random).getMovementComponent().getPosition()[0]+ enemyList.get(random).getMovementComponent().getEntitySize()[0]/2,
                enemyList.get(random).getMovementComponent().getPosition()[1],
                GameCellsX/50, GameCellsY/50));
        projectileList.add(projectile);
        allEntities.addAll(projectileList);
    }

    /**
     * visualises all the characters
     */
    public void characterVisual() {
        for (Entity e : allEntities) {
            e.Visual();
        }
    }

    /**
     * creates the levels
     * @param level
     * @throws IOException
     */
    public void createLevel(int level) throws IOException {
        createEnemy(level);
    }

    /**
     * spawn bonus bullets
     */
    public void createBonusBullet() {
        Random rand = new Random();
        int upperbound = (int) GameCellsX;
        int random = rand.nextInt(upperbound);
        allEntities.add(f.createBonusBullet(new MovementComponent(0,10,random,GameCellsY/10,GameCellsX/25, GameCellsX/25)));
    }

    /**
     * creates the game over screen and visualises it
     */
    public void gameOverScreen() {
        GameOverScreenEntity GOScreen = f.createGameOverScreen(new MovementComponent(0,0,0,0,GameCellsX,GameCellsY));
        GOScreen.Visual();
    }

    /**
     * creates the start of the game where itll be decided if you will play with one player or two
     */
    public void startOfGame() {
        f.setGameDimensions(GameCellsX, GameCellsY);
        StartScreenEntity SS = f.createStartScreen(new KeyboardInputs1(f.frame), new MovementComponent(0,0,0,0,GameCellsX,GameCellsY));
        SS.Visual();
        if (SS.getInputs().isOnePlayer) {
            this.amountOfPlayers = 1;
            this.start = true;
        } else if (SS.getInputs().isTwoPlayer) {
            this.amountOfPlayers = 2;
            this.start = true;
        }
    }

    /**
     * creates the score overlay
     */
    public void createScoreOverlay() {
        InformationOutputEntity SCOREOVERL = f.createScoreOutput(
                new MovementComponent(0,0,GameCellsY/50,GameCellsY -  GameCellsY/10,GameCellsX,GameCellsY)
        );
        allEntities.add(SCOREOVERL);
    }

    /**
     * updates the score overlay
     */
    public void updateScoreOverlay() {
        for (Entity SC : allEntities) {
            if (SC instanceof InformationOutputEntity SO) {
                int ct = 0;
                for (Entity player : allEntities) {
                    if (player instanceof PlayerEntity pl) {
                        ct += 1;
                        if (ct == 1) {
                            SO.setScore1(pl.getScore());
                            SO.setHealth1(pl.getHealth());
                        } else if (ct == 2) {
                            SO.setScore2(pl.getScore());
                            SO.setHealth2(pl.getHealth());
                        }
                    }
                }
                SO.setLevel(this.level);
            }
        }
    }

    //checkIf----------------------------------------------------------

    /**
     * checks if all enemy entities are gone. if this is the case, itll create a new level
     *
     * @param EL entity list
     * @throws IOException if IO cannot be read
     */
    public void checkIfEmpty(List<Entity> EL) throws IOException {
        boolean isEmpty=true;
        for (Entity e : EL) {
            if (e instanceof EnemyEntity) {
                isEmpty=false;
                break;
            }
        }
        if (isEmpty) {
            level += 1;
            createLevel(level);
        }
    }

    /**
     * check if the health of the player is zero OR the spaceships collides with the enemy
     *
     * @param EL entity list
     */
    public void checkGameOver(List<Entity> EL) {
        for (Entity e : EL) {
            if (e instanceof PlayerEntity) {
                if ((((PlayerEntity) e).getHealth() == 0) || updaterCS.spaceshipAndEnemyCollision(EL)) {
                    isRunning = false;
                    break;
                }
            }
        }

    }

//start van gameloop----------------------------------------------------------------------------------------------------
    public void Start() throws Exception {
        while (waitingForStart) {
            startOfGame();
            while (start) {
                f.render();
                Init();
                while (isRunning) {
                    checkInput();
                    if (!isPaused) {

                        //update de posities
                        componentList.clear();
                        for (Entity e : allEntities) {
                            componentList.add(e.getMovementComponent());
                        }
                        updaterMS.update(componentList, stopwatch.getDeltaTime());

                        //random projectile spawns
                        if (this.projectileCounter > this.timeBetweenProjectiles) {
                            createEnemyProjectile(allEntities);
                            this.projectileCounter = 0;
                        } else {
                            this.projectileCounter += stopwatch.getDeltaTime();
                        }

                        //random bonus
                        if (this.timeBonusSpawn > 15) {
                            createBonusBullet();
                            this.timeBonusSpawn = 0;
                        } else {
                            this.timeBonusSpawn += stopwatch.getDeltaTime();
                        }
                        List<Entity> BonusList = updaterCS.checkBonusBullet(allEntities, GameCellsY);
                        if (BonusList.size() > 0) {
                            if (BonusList.get(0).getMovementComponent().getPosition()[1] < GameCellsY) {
                                durationBonus = 2.5;
                                stillMoreBullets = true;
                            }
                        } else {
                            durationBonus -= stopwatch.getDeltaTime();
                        }
                        if (durationBonus < 0) {
                            stillMoreBullets = false;
                        }
                        for (Entity e : BonusList) {
                            allEntities.remove(e);
                        }

                        //check of de enemies de rand raken
                        switch (level) {
                            case 1 -> {
                                updaterCS.collisionBorder(allEntities, GameCellsX, GameCellsY, 6);
                            }
                            case 2 -> {
                                updaterCS.collisionBorder(allEntities, GameCellsX, GameCellsY, 4);
                            }
                            default -> {
                                updaterCS.collisionBorder(allEntities, GameCellsX, GameCellsY, 2);
                            }
                        }

                        //check of de player de rand raakt
                        updaterCS.collisionX(allEntities, GameCellsX);

                        //check of de bullets de enemy raken en haal de ongecolissioned bullets uit de lijst
                        //voegt punten door
                        List<Entity> DeadList = updaterCS.checkBulletCollision(allEntities);
                        for (Entity e : DeadList) {
                            allEntities.remove(e);
                        }
                        // update punten naar scherm
                        updateScoreOverlay();

                        //check of player wordt gehit
                        List<Entity> ProjectileList = updaterCS.checkProjectileCollision(allEntities);
                        for (Entity e : ProjectileList) {
                            allEntities.remove(e);
                        }

                        //check of de player health == 0 of spaceship enemy in positie raakt
                        checkGameOver(allEntities);

                        //check of al de enemies dood zijn
                        checkIfEmpty(allEntities);

                    }
                    characterVisual();
                    Thread.sleep(16);
                    f.render();
                    stopwatch.updateTime();
                    //System.out.println("FPS: " + 1/stopwatch.getDeltaTime());

                }
                f.render();
                gameOverScreen();
            }

        }
    }

}
