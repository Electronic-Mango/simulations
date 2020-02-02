package task0.gui;

import gui.drawboard.DrawBoard;
import gui.drawboard.SimulationComponent;
import task0.simulator.ProjectileThrow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ProjectileThrowDrawBoard extends DrawBoard {

    private final List<SimulationComponent<ProjectileThrow>> projectileThrowTrajectories = new ArrayList<>();
    private List<CompletableFuture<Void>> projectileThrowFutureList = new ArrayList<>();

    public ProjectileThrowDrawBoard() {
        setBackground(Color.WHITE);
    }

    public void prepareSimulation(final SimulationComponent<ProjectileThrow> projectileThrowTrajectory) {
        projectileThrowTrajectories.add(projectileThrowTrajectory);
    }

    @Override
    public void runSimulation() {
        super.runSimulation();
        final SimulationComponent<ProjectileThrow> latestProjectileThrowTrajectory =
                projectileThrowTrajectories.get(projectileThrowTrajectories.size() - 1);
        projectileThrowFutureList.add(latestProjectileThrowTrajectory.runSimulation());
    }

    @Override
    public void clearSimulation() {
        projectileThrowTrajectories.forEach(SimulationComponent::stop);
        projectileThrowFutureList.forEach(CompletableFuture::join);
        projectileThrowTrajectories.clear();
        projectileThrowFutureList.clear();
        repaint();
    }

    @Override
    protected boolean isSimulationComplete() {
        return projectileThrowFutureList.stream().allMatch(CompletableFuture::isDone);
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        super.actionPerformed(event);
    }

    @Override
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.translate(0, getHeight());
        graphics2D.scale(1, -1);
        projectileThrowTrajectories.forEach(projectileThrowTrajectory ->
                projectileThrowTrajectory.drawSimulationResults(graphics2D));
    }

}
