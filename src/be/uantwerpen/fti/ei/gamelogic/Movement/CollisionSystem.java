package be.uantwerpen.fti.ei.gamelogic.Movement;

import be.uantwerpen.fti.ei.gamelogic.Entities.*;
import be.uantwerpen.fti.ei.gamelogic.Helpers.Constants;
import be.uantwerpen.fti.ei.gamelogic.Inputs.AbstractInput;

import java.util.ArrayList;
import java.util.List;

public class CollisionSystem {
    private int amountOfBounces = 0;

    /**
     * checks whether the enemy entity has collided with the borders with the game
     * @param EL: entitiy list
     * @param x: x-size of the game
     * @param y: y-size of the game
     * @param bounces: amount of bounces the enemies have to do before they go to the next line
     */
    public void collisionBorder(List<Entity> EL, double x, double y, int bounces) {
        for (Entity e : EL) {
            if (e instanceof EnemyEntity) {
                if (this.amountOfBounces > bounces) {
                    this.amountOfBounces = 0;
                    for (Entity e1 : EL) {
                        if (e1 instanceof EnemyEntity) {
                            e1.getMovementComponent().setPosition(new double[] {e1.getMovementComponent().getPosition()[0], e1.getMovementComponent().getPosition()[1]+y/20});
                        }
                    }
                }

                if (e.getMovementComponent().getPosition()[0] < 0 && ((EnemyEntity) e).getCurrentState() == 2) {
                    // dus als hij naar links gaat en kleiner is dan de linkse y border
                    for (Entity e1 : EL) {
                        if (e1 instanceof EnemyEntity) {
                            ((EnemyEntity) e1).setCurrentState(1);
                            e1.getMovementComponent().setVelocity(new double[] {e1.getMovementComponent().getVelocity()[0] *-1, e1.getMovementComponent().getVelocity()[1]});
                        }
                    }
                    this.amountOfBounces += 1;
                    break;
                }
                else if (e.getMovementComponent().getPosition()[0] > x- e.getMovementComponent().getEntitySize()[0] && ((EnemyEntity) e).getCurrentState() == 1) {
                    // dus als hij naar rechts gaat en de rechtse y border raakt
                    for (Entity e1 : EL) {
                        if (e1 instanceof EnemyEntity) {
                            ((EnemyEntity) e1).setCurrentState(2);
                            e1.getMovementComponent().setVelocity(new double[] {e1.getMovementComponent().getVelocity()[0] *-1, e1.getMovementComponent().getVelocity()[1]});
                        }
                    }
                    amountOfBounces += 1;
                    break;
                }
            }
        }
    }

    /**
     * Checks and stops the player if it goes beyond the borders
     * @param EL: entity list
     * @param x: x-size of the game
     */
    public void collisionX(List<Entity> EL, double x) {
        for (Entity e : EL) {
            if (e instanceof PlayerEntity) {
                if (e.getMovementComponent().getPosition()[0] < 0) {
                    e.getMovementComponent().setPosition(new double[] {0, e.getMovementComponent().getPosition()[1]});
                } else if (e.getMovementComponent().getPosition()[0] > x-e.getMovementComponent().getEntitySize()[0]) {
                    e.getMovementComponent().setPosition(new double[]{x - e.getMovementComponent().getEntitySize()[0], e.getMovementComponent().getPosition()[1]});
                }
            }
        }
    }

    /**
     * checks whether the bullets collide with the enemies
     * @param EL: entity list
     * @return a list with the removable entities
     */
    public List<Entity> checkBulletCollision(List<Entity> EL) {
        List<Entity> DeadList = new ArrayList<>();
        for (Entity e : EL) {
            if (e instanceof PlayerBulletEntity) {
                for (Entity ent : EL) {
                    if (ent instanceof EnemyEntity) {
                        double BulletX = e.getMovementComponent().getPosition()[0];
                        double BulletY = e.getMovementComponent().getPosition()[1];
                        double EnemyX = ent.getMovementComponent().getPosition()[0];
                        double EnemyY = ent.getMovementComponent().getPosition()[1];
                        double EnemySizeX = ent.getMovementComponent().getEntitySize()[0];
                        double EnemySizeY = ent.getMovementComponent().getEntitySize()[1];
                        if ((BulletX > EnemyX) && (BulletX < EnemyX + EnemySizeX) &&
                                (BulletY > EnemyY) && (BulletY < EnemyY + EnemySizeY)) {
                            DeadList.add(e);
                            DeadList.add(ent);
                            for (Entity player : EL) {
                                if (player instanceof PlayerEntity) {
                                    ((PlayerEntity) player).setScore(((PlayerEntity) player).getScore()+50);
                                }
                            }
                        }
                    } if (ent.getMovementComponent().getPosition()[1] < 0) {
                        DeadList.add(ent);
                    }
                }
            }
        } return DeadList;
    }

    /**
     * Checks whether the projectiles of the enemies collide with the player or go beyond the y border
     * @param EL: entity list
     * @return a list of projectiles to remove (the ones that went beyond the border)
     */
    public List<Entity> checkProjectileCollision(List<Entity> EL) {
        List<Entity> DeadList = new ArrayList<>();
        for (Entity e : EL) {
            if (e instanceof EnemyProjectileEntity) {
                for (Entity player : EL) {
                    if (player instanceof PlayerEntity play) {
                        double ProjX = e.getMovementComponent().getPosition()[0];
                        double ProjY = e.getMovementComponent().getPosition()[1];
                        double playerX = player.getMovementComponent().getPosition()[0];
                        double playerY = player.getMovementComponent().getPosition()[1];
                        double playerSizeX = player.getMovementComponent().getEntitySize()[0];
                        double playerSizeY = player.getMovementComponent().getEntitySize()[1];
                        if (ProjX > playerX && ProjX < playerX + playerSizeX &&
                                ProjY > playerY && ProjY < playerY + playerSizeY) {
                            play.setHealth(play.getHealth()-1);
                            DeadList.add(e);
                        }
                    }
                }
            }
        } return DeadList;
    }

    /**
     * checks whether the enemy and player collide
     * @param EL: entity list
     * @return a boolean which indicates if they collided or not
     */
    public boolean spaceshipAndEnemyCollision(List<Entity> EL) {
        for (Entity e : EL) {
            if (e instanceof PlayerEntity player) {
                for (Entity enemy : EL) {
                    if (enemy instanceof EnemyEntity) {
                        if (player.getMovementComponent().getPosition()[1] < enemy.getMovementComponent().getPosition()[1]+enemy.getMovementComponent().getEntitySize()[1]) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if the player picks the bonus up
     * @param EL: entity list
     * @param GameCellsY: y-size of the game
     * @return a list with removable entities
     */
    public List<Entity> checkBonusBullet(List<Entity> EL, double GameCellsY) {
        List<Entity> DeadList = new ArrayList<>();
        for (Entity bonus : EL) {
            if (bonus instanceof BonusEntityBullets bullet) {
                for (Entity player : EL) {
                    if (player instanceof PlayerEntity) {
                        double x = player.getMovementComponent().getPosition()[0];
                        double y = player.getMovementComponent().getPosition()[1];
                        double xSize = player.getMovementComponent().getEntitySize()[0];
                        double ySize = player.getMovementComponent().getEntitySize()[1];
                        double xBonus = bullet.getMovementComponent().getPosition()[0];
                        double yBonus = bullet.getMovementComponent().getPosition()[1];
                        double xBSize = bullet.getMovementComponent().getEntitySize()[0];
                        double yBSize = bullet.getMovementComponent().getEntitySize()[1];

                        if (    ( (x < xBonus && xBonus < x+xSize)                  // x < xBonus < x+xSize
                               || (xBonus < x && x < xBonus+xBSize)                 // xbonus < x < xbonus+xBSize
                               || (xBonus < x+xSize && x+xSize < xBonus+xBSize) )   // xBonus < x+xSize < xBonus/xBSize
                               && (yBonus < y && y < yBonus+yBSize) )
                        {
                            DeadList.add(bullet);
                        } else if ( yBonus > GameCellsY) {
                            DeadList.add(bullet);
                        }
                    }
                }
            }
        } return DeadList;
    }
}
