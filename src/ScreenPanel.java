import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import Objects.*;

public class ScreenPanel extends JPanel implements MouseListener {
    private ArrayList<Tube> mTubesList;
    private final int SMALLEST_NUM_EMPTY_TUBES = 1;
    private final int LARGEST_NUM_EMPTY_TUBES = 2;
    private final int MIN_NUM_TUBES_TWO_EMPTY = 5;
    private final int NUM_BALLS_PER_TUBE = 4;
    private final ArrayList<Tube> mSelectedTubes = new ArrayList(2);

    public ScreenPanel() {

    }

    static class ClicksPanel extends MouseAdapter implements Observer {
        private JLabel statusBar = new JLabel();
        @Override
        public void update(Observer o, int moves) {
            statusBar.setText(String.format("Ati dat click de %d ori", moves));
        }

    }

    public void addTubes(int aNumTubes) {
        this.mTubesList = new ArrayList(aNumTubes);

        for(int i = 0; i < aNumTubes; ++i) {
            Tube tTube = new Tube(i + 1);
            tTube.addMouseListener(this);
            this.mTubesList.add(tTube);
            this.add(tTube);
        }

        this.setBalls(aNumTubes);
    }

    public void mouseClicked(MouseEvent e) {
        Object tClickSource = e.getSource();
        if (tClickSource instanceof Tube) {
            final Tube tTube = (Tube)e.getSource();
            this.mSelectedTubes.add(tTube);
            if (this.mSelectedTubes.size() > 2) {
                this.resetSelectedTubes();
            } else if (this.mSelectedTubes.size() > 0) {
                if (tTube.getColor().equals(Color.BLACK)) {
                    tTube.setTubeColor(Color.WHITE);
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            tTube.repaint();
                        }
                    });
                } else {
                    tTube.setTubeColor(Color.BLACK);
                    this.resetSelectedTubes();
                }
            }

            if (this.mSelectedTubes.size() == 2) {
                if (((Tube)this.mSelectedTubes.get(0)).getBallList().isEmpty()) {
                    this.resetSelectedTubes();
                } else if (((Tube)this.mSelectedTubes.get(1)).getBallList().size() < 4) {
                    ((Tube)this.mSelectedTubes.get(1)).addBall(((Tube)this.mSelectedTubes.get(0)).getBall());
                    this.resetSelectedTubes();
                } else {
                    JOptionPane.showMessageDialog(this, "Balls can only be added to empty tubes :)", "Ge" +
                            "Info", 1);
                    this.resetSelectedTubes();
                }
            }
        } else {
            this.resetSelectedTubes();
        }

        if (this.isWinCondition()) {
            JOptionPane.showMessageDialog(this, "Congratulations, YOU WON!\nReset to play again.", "WINNER!", 1);
        }

    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void resetPanel() {
        if (!this.mSelectedTubes.isEmpty()) {
            this.resetSelectedTubes();
        }

    }

    private boolean isWinCondition() {
        boolean tWinner = true;
        if (this.mTubesList != null) {
            Iterator var2 = this.mTubesList.iterator();

            do {
                Tube tTube;
                do {
                    if (!var2.hasNext()) {
                        return tWinner;
                    }

                    tTube = (Tube)var2.next();
                } while(tTube.getBallList().isEmpty());

                Color tBallColor = ((Ball)tTube.getBallList().get(0)).getColor();

                for(int i = 1; i < tTube.getBallList().size(); ++i) {
                    if (!tBallColor.equals(((Ball)tTube.getBallList().get(i)).getColor()) || tTube.getBallList().size() != 4) {
                        tWinner = false;
                        break;
                    }
                }
            } while(tWinner);
        } else {
            tWinner = false;
        }

        return tWinner;
    }

    private void resetSelectedTubes() {
        for(int i = 0; i < this.mSelectedTubes.size(); ++i) {
            ((Tube)this.mSelectedTubes.get(i)).setTubeColor(Color.BLACK);
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ScreenPanel.this.repaint();
            }
        });
        this.mSelectedTubes.clear();
    }

    private void setBalls(int aNumTubes) {
        int tTotalNumBalls;
        byte tNumEmptyTubes;
        if (aNumTubes >= 5) {
            tTotalNumBalls = (aNumTubes - 2) * 4;
            tNumEmptyTubes = 2;
        } else {
            tTotalNumBalls = (aNumTubes - 1) * 4;
            tNumEmptyTubes = 1;
        }

        ArrayList<Color> tBallColors = this.calculateBallsPerTube(tTotalNumBalls);
        Ball tBall = null;
        Random tRandom = new Random();

        for(int i = 0; i < this.mTubesList.size() - tNumEmptyTubes; ++i) {
            int tBallIndex = 1;

            Stack tBallList;
            for(tBallList = new Stack(); tBallList.size() != 4; ++tBallIndex) {
                int tRandomIndex = tRandom.nextInt(tBallColors.size());
                tBall = new Ball(tBallIndex, (Color)tBallColors.get(tRandomIndex));
                tBallColors.remove(tRandomIndex);
                tBallList.add(tBall);
            }

            ((Tube)this.mTubesList.get(i)).setBallList(tBallList);
        }

    }

    private ArrayList<Color> calculateBallsPerTube(int aTotalNumBalls) {
        ArrayList<Color> tColors = new ArrayList(aTotalNumBalls);
        int i;
        switch (aTotalNumBalls) {
            case 8:
                for(i = 0; i < aTotalNumBalls; ++i) {
                    if (i < aTotalNumBalls / 2) {
                        tColors.add(Color.magenta);
                    } else {
                        tColors.add(Color.blue);
                    }
                }

                return tColors;
            case 12:
                for(i = 0; i < aTotalNumBalls; ++i) {
                    if (i < aTotalNumBalls / 3) {
                        tColors.add(Color.magenta);
                    } else if (i < aTotalNumBalls / 3 * 2) {
                        tColors.add(Color.blue);
                    } else {
                        tColors.add(Color.red);
                    }
                }

                return tColors;
            case 16:
                for(i = 0; i < aTotalNumBalls; ++i) {
                    if (i < aTotalNumBalls / 4) {
                        tColors.add(Color.magenta);
                    } else if (i < aTotalNumBalls / 4 * 2) {
                        tColors.add(Color.blue);
                    } else if (i < aTotalNumBalls / 4 * 3) {
                        tColors.add(Color.red);
                    } else {
                        tColors.add(Color.orange);
                    }
                }

                return tColors;
            case 20:
                for(i = 0; i < aTotalNumBalls; ++i) {
                    if (i < aTotalNumBalls / 5) {
                        tColors.add(Color.magenta);
                    } else if (i < aTotalNumBalls / 5 * 2) {
                        tColors.add(Color.blue);
                    } else if (i < aTotalNumBalls / 5 * 3) {
                        tColors.add(Color.red);
                    } else if (i < aTotalNumBalls / 5 * 4) {
                        tColors.add(Color.orange);
                    } else {
                        tColors.add(Color.black);
                    }
                }

                return tColors;
            default:
                tColors.clear();
                return tColors;
        }
    }
}

